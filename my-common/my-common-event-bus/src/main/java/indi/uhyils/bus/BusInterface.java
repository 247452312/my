package indi.uhyils.bus;

import com.rabbitmq.client.Consumer;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.pojo.cqe.event.base.BaseParentEvent;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月29日 21时51分
 */
public interface BusInterface extends Consumer {

    /**
     * 事件路由名称
     */
    String BUS_EVENT_EXCHANGE_NAME = "BUS_EVENT_EXCHANGE_NAME";

    /**
     * 事件队列
     */
    String BUS_EVENT_QUEUE_NAME = "BUS_EVENT_QUEUE_NAME";

    /**
     * 提交事件
     *
     * @param event
     */
    void commit(BaseParentEvent event);

    /**
     * 批量提交事件
     *
     * @param events
     */
    void commit(List<BaseParentEvent> events);

    /**
     * 清空事件
     */
    void cleanCommitEvent();

    /**
     * 发布事件
     */
    void pushEvent();

    /**
     * 批量提交并发送事件 (不发送存储事件)
     *
     * @param baseEvents
     */
    void commitAndPush(List<BaseParentEvent> baseEvents);

    /**
     * 提交并发送事件 (不发送存储事件)
     *
     * @param baseEvents
     */
    void commitAndPush(BaseParentEvent baseEvents);

    /**
     * 异步发布事件
     */
    void asyncPushEvent();

    /**
     * 批量异步提交并发送事件 (不发送存储事件)
     *
     * @param baseEvents
     */
    void asyncCommitAndPush(List<BaseParentEvent> baseEvents);

    /**
     * 异步提交并发送事件 (不发送存储事件)
     *
     * @param baseEvent
     */
    void asyncCommitAndPush(BaseParentEvent baseEvent);

    /**
     * 移除还没有发布的指定事件(包括子类)
     *
     * @param baseEventClass
     *
     * @return
     */
    List<BaseEvent> remove(Class<? extends BaseEvent> baseEventClass);

    /**
     * 精准移除还没有发布的指定事件(不包括子类)
     *
     * @param baseEventClass
     *
     * @return
     */
    List<BaseEvent> preciseRemove(Class<? extends BaseEvent> baseEventClass);

    /**
     * 精准获取未发布的事件(不包括子类)
     *
     * @param baseEventClass
     *
     * @return
     */
    List<BaseEvent> preciseGet(Class<? extends BaseEvent> baseEventClass);

    /**
     * 获取未发布的事件(包括子类)
     *
     * @param baseEventClass
     *
     * @return
     */
    List<BaseEvent> get(Class<? extends BaseEvent> baseEventClass);
}
