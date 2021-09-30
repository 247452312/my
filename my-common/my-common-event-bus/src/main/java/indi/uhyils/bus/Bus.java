package indi.uhyils.bus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.pojo.cqe.event.base.BaseParentEvent;
import indi.uhyils.protocol.register.base.Register;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 09时20分
 */
public class Bus extends DefaultConsumer implements BusInterface {


    /**
     * class信息在json中的key
     */
    private static final String CLASS_JSON_KEY = "class";

    /**
     * 注册者
     */
    private final List<Register> registers;

    public ThreadLocal<List<BaseEvent>> events = new InheritableThreadLocal<>();

    public Bus(Channel channel, List<Register> registers) {
        super(channel);
        events.set(new ArrayList<>());
        this.registers = registers;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONObject.parseObject(text);
        Asserts.assertTrue(jsonObject.containsKey(CLASS_JSON_KEY), "json中不存在class");

        String className = jsonObject.getString(CLASS_JSON_KEY);

        BaseEvent event = null;
        try {
            event = JSONObject.parseObject(text, (Type) Class.forName(className));
        } catch (ClassNotFoundException e) {
            LogUtil.error(e);
        }
        Asserts.assertTrue(event != null);
        doPublishEvent(Collections.singletonList(event));
    }


    /**
     * 提交事件
     *
     * @param event
     */
    @Override
    public void commit(BaseParentEvent event) {
        List<BaseEvent> baseEvents = this.events.get();
        baseEvents.addAll(event.transToBaseEvent());
    }

    /**
     * 提交事件
     */
    @Override
    public void commit(List<BaseParentEvent> events) {
        events.forEach(this::commit);
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
        doPublishEvent(baseEvents);
    }

    /**
     * 提交发布事件
     *
     * @param baseEvents
     */
    @Override
    public void commitAndPush(List<BaseParentEvent> baseEvents) {
        if (CollectionUtil.isEmpty(baseEvents)) {
            return;
        }
        baseEvents.forEach(this::commitAndPush);
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
        doPublishEvent(baseEvents.transToBaseEvent());
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
     */
    @Override
    public void syncPushEvent() {
        List<BaseEvent> baseEvents = events.get();
        if (CollectionUtil.isEmpty(baseEvents)) {
            return;
        }
        doSyncPublishEvent(baseEvents);
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
            String msg = JSON.toJSONString(next);
            JSONObject jsonObject = JSON.parseObject(msg);
            Asserts.assertTrue(!jsonObject.containsKey(CLASS_JSON_KEY));
            jsonObject.put(CLASS_JSON_KEY, next.getClass().getName());
            msg = jsonObject.toJSONString();
            MqUtil.sendMsg(BUS_EVENT_EXCHANGE_NAME, BUS_EVENT_QUEUE_NAME, msg);
            iterator.remove();
        }
    }

    /**
     * 异步提交发布事件
     *
     * @param baseEvents
     */
    @Override
    public void syncCommitAndPush(List<BaseParentEvent> baseEvents) {
        if (CollectionUtil.isEmpty(baseEvents)) {
            return;
        }
        baseEvents.forEach(this::syncCommitAndPush);
    }

    /**
     * 异步提交发布事件
     *
     * @param baseEvent
     */
    @Override
    public void syncCommitAndPush(BaseParentEvent baseEvent) {
        if (baseEvent == null) {
            return;
        }
        doSyncPublishEvent(baseEvent.transToBaseEvent());
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


}
