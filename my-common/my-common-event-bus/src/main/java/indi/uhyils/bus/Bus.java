package indi.uhyils.bus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.MyThreadLocal;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.pojo.cqe.event.base.BaseParentEvent;
import indi.uhyils.pojo.cqe.event.base.PackageEvent;
import indi.uhyils.protocol.register.base.Register;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.EventUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 09时20分
 */
public class Bus extends DefaultConsumer implements BusInterface {

    /**
     * 注册者
     */
    private final List<Register> registers;

    public MyThreadLocal<List<BaseEvent>> events = new MyThreadLocal<>();

    public Bus(Channel channel, List<Register> registers) {
        super(channel);
        events.set(new ArrayList<>());
        this.registers = registers;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        BaseEvent event = JSONObject.parseObject(text, BaseEvent.class);
        Asserts.assertTrue(event != null);
        // 解析事件(打包,父类事件转为子类事件)
        List<BaseEvent> trans = EventUtil.trans(event);
        doPublishEvent(trans);
    }

    /**
     * 提交事件
     *
     * @param event
     */
    @Override
    public void commit(BaseParentEvent event) {
        List<BaseEvent> baseEvents = this.events.get();
        // 打包事件不进行分解
        if (event instanceof PackageEvent) {
            baseEvents.add(event);
        } else {
            // 其他父类事件进行分解
            baseEvents.addAll(event.transToBaseEvent());
        }
    }

    /**
     * 提交事件
     */
    @Override
    public void commit(BaseParentEvent... events) {
        for (BaseParentEvent event : events) {
            commit(event);
        }
    }

    @Override
    public void commitAndPushWithTransactional(BaseParentEvent... events) {
        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        // 没有事务的时候走正常的路径
        if (!actualTransactionActive) {
            commitAndPush(events);
            return;
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                commitAndPush(events);
            }

        });
    }

    @Override
    public void commitAndPushWithTransactional(BaseParentEvent events) {
        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        // 没有事务的时候走正常的路径
        if (!actualTransactionActive) {
            commitAndPush(events);
            return;
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                commitAndPush(events);
            }
        });
    }

    /**
     * 清空待发布的事件
     */
    @Override
    public void cleanCommitEvent() {
        events.get().clear();
    }

    /**
     * 发布事件
     */
    @Override
    public void pushEvent() {
        List<BaseEvent> baseEvents = events.get();
        if (CollectionUtil.isEmpty(baseEvents)) {
            return;
        }
        // 父类事件(打包事件)转化为子类事件
        List<BaseEvent> trans = EventUtil.trans(baseEvents);
        doPublishEvent(trans);
    }

    /**
     * 提交发布事件
     *
     * @param baseEvents
     */
    @Override
    public void commitAndPush(BaseParentEvent... baseEvents) {
        if (CollectionUtil.isEmpty(baseEvents)) {
            return;
        }
        for (BaseParentEvent baseEvent : baseEvents) {
            commitAndPush(baseEvent);
        }
    }

    /**
     * 提交发布事件
     *
     * @param baseEvents
     */
    @Override
    public void commitAndPush(BaseParentEvent baseEvents) {
        if (baseEvents == null) {
            return;
        }
        // 同步发布事件均转化为子类事件发布
        doPublishEvent(baseEvents.transToBaseEvent());
    }

    /**
     * 异步发布事件
     */
    @Override
    public void asyncPushEvent() {
        List<BaseEvent> baseEvents = events.get();
        if (CollectionUtil.isEmpty(baseEvents)) {
            return;
        }
        // 不需要进行转化,直接发布,具体转化在消费处进行转化
        doSyncPublishEvent(baseEvents);
    }

    /**
     * 异步提交发布事件
     *
     * @param baseEvents
     */
    @Override
    public void asyncCommitAndPush(BaseParentEvent... baseEvents) {
        if (CollectionUtil.isEmpty(baseEvents)) {
            return;
        }
        for (BaseParentEvent baseEvent : baseEvents) {
            asyncCommitAndPush(baseEvent);
        }
    }

    /**
     * 异步提交发布事件
     *
     * @param baseEvent
     */
    @Override
    public void asyncCommitAndPush(BaseParentEvent baseEvent) {
        if (baseEvent == null) {
            return;
        }
        // 验证,是否是打包事件
        if (baseEvent instanceof PackageEvent) {
            // 打包事件不分解
            List<BaseEvent> result = CollectionUtil.buildArrayList(baseEvent);
            doSyncPublishEvent(result);
            return;
        }
        // 父类事件分解后发布
        doSyncPublishEvent(baseEvent.transToBaseEvent());
    }

    /**
     * 移除还没有发布的事件
     *
     * @param baseEventClass 事件的类型
     *
     * @return 被移除的事件
     */
    @Override
    public List<BaseEvent> remove(Class<? extends BaseEvent> baseEventClass) {
        List<BaseEvent> result = new ArrayList<>();
        List<BaseEvent> baseEvents = events.get();
        Iterator<BaseEvent> iterator = baseEvents.iterator();
        while (iterator.hasNext()) {
            BaseEvent next = iterator.next();
            Class<? extends BaseEvent> childClass = next.getClass();
            // 事件的子类也会被移除
            if (baseEventClass.isAssignableFrom(childClass)) {
                iterator.remove();
                result.add(next);
            }
        }
        return result;
    }

    /**
     * 精准移除还没有发布的事件
     *
     * @param baseEventClass 事件的类型
     *
     * @return 被移除的事件
     */
    @Override
    public List<BaseEvent> preciseRemove(Class<? extends BaseEvent> baseEventClass) {
        List<BaseEvent> result = new ArrayList<>();
        List<BaseEvent> baseEvents = events.get();
        Iterator<BaseEvent> iterator = baseEvents.iterator();
        while (iterator.hasNext()) {
            BaseEvent next = iterator.next();
            if (Objects.equals(baseEventClass, next.getClass())) {
                iterator.remove();
                result.add(next);
            }
        }
        return result;
    }

    /**
     * 精准获取还没有发布的事件
     *
     * @param baseEventClass
     *
     * @return 还没有发布的指定事件
     */
    @Override
    public List<BaseEvent> preciseGet(Class<? extends BaseEvent> baseEventClass) {
        List<BaseEvent> result = new ArrayList<>();
        List<BaseEvent> baseEvents = events.get();
        for (BaseEvent next : baseEvents) {
            if (Objects.equals(next.getClass(), baseEventClass)) {
                result.add(next);
            }
        }
        return result;
    }

    /**
     * 精准获取还没有发布的事件
     *
     * @param baseEventClass
     *
     * @return 还没有发布的指定事件
     */
    @Override
    public List<BaseEvent> get(Class<? extends BaseEvent> baseEventClass) {
        List<BaseEvent> result = new ArrayList<>();
        List<BaseEvent> baseEvents = events.get();
        for (BaseEvent next : baseEvents) {
            if (baseEventClass.isAssignableFrom(next.getClass())) {
                result.add(next);
            }
        }
        return result;
    }

    /**
     * 发布事件
     *
     * @param events
     */
    private void doPublishEvent(List<BaseEvent> events) {
        Iterator<BaseEvent> iterator = events.iterator();
        while (iterator.hasNext()) {
            BaseEvent next = iterator.next();
            // 遍历所有的监听者
            for (Register register : registers) {
                List<Class<? extends BaseEvent>> classes = register.targetEvent();
                Asserts.assertTrue(CollectionUtil.isNotEmpty(classes), "监听者监听的事件不能为空");
                // 遍历所有的待发布事件
                if (matchingEvent(register, next)) {
                    register.onEvent(next);
                }
            }
            // 发布后删除事件
            iterator.remove();
        }
    }

    /**
     * 异步发布事件
     *
     * @param baseEvents
     */
    private void doSyncPublishEvent(List<BaseEvent> baseEvents) {
        Iterator<BaseEvent> iterator = baseEvents.iterator();
        while (iterator.hasNext()) {
            BaseEvent next = iterator.next();
            String msg = JSON.toJSONString(next, SerializerFeature.WriteClassName);
            MqUtil.sendMsg(BUS_EVENT_EXCHANGE_NAME, BUS_EVENT_QUEUE_NAME, msg);
            iterator.remove();
        }
    }

    /**
     * 是否可以匹配到事件
     * 发布父类事件子类不能收到,但是发布子类事件父类可以监听到
     * 例: 跑步运动员监听跑步发令枪,游泳运动员监听游泳发令枪, 发布发令枪事件时无反应,因为不知道是不是自己的发令枪, 但是监听发令枪的计时事件不关心是哪一个发令枪,所以两个发令枪都可以使计时事件开始
     *
     * @param register  事件监听器
     * @param baseEvent 事件
     *
     * @return
     */
    private boolean matchingEvent(Register register, BaseEvent baseEvent) {
        // 遍历所有这个监听者监听的事件类型
        for (Class<? extends BaseEvent> eventClass : register.targetEvent()) {
            // 父类.isAssignableFrom(子类)
            if (eventClass.isAssignableFrom(baseEvent.getClass())) {
                // 一个监听者只能监听一个事件一次
                return true;
            }
        }
        return false;
    }


}
