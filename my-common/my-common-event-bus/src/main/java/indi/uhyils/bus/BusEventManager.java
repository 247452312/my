package indi.uhyils.bus;

import indi.uhyils.bus.listener.EventListener;
import indi.uhyils.pojo.cqe.event.BaseEvent;
import indi.uhyils.util.SpringUtil;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月12日 17时45分
 */
public class BusEventManager {

    /**
     * 注册的事件们
     */
    private final List<EventListener> eventListener;

    public BusEventManager(List<EventListener> eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * 动态注册
     *
     * @param listener
     */
    public static void register(EventListener listener) {
        BusEventManager bean = SpringUtil.getBean(BusEventManager.class);
        bean.eventListener.add(listener);
    }

    /**
     * 推送事件
     *
     * @param event
     * @param <T>
     */
    public static <T extends BaseEvent> void push(T event) {
        BusEventManager bean = SpringUtil.getBean(BusEventManager.class);
        bean.pushEvent(event);
    }

    /**
     * 异步推送事件
     *
     * @param event
     * @param <T>
     */
    public static <T extends BaseEvent> void syncPush(T event) {
        CompletableFuture.runAsync(() -> push(event));
    }

    /**
     * 推送事件
     *
     * @param event
     * @param <T>
     */
    public static <T extends BaseEvent> void push(Class<T> event) {
        BusEventManager bean = SpringUtil.getBean(BusEventManager.class);
        bean.pushEvent(event);
    }

    /**
     * 异步推送事件
     *
     * @param event
     * @param <T>
     */
    public static <T extends BaseEvent> void syncPush(Class<T> event) {
        CompletableFuture.runAsync(() -> push(event));
    }

    private <T extends BaseEvent> void pushEvent(T event) {
        eventListener.stream().filter(t -> t.getReceiveEventClass().isAssignableFrom(event.getClass())).forEach(t -> t.execute(event));
    }

    private <T extends BaseEvent> void pushEvent(Class<T> event) {
        eventListener.stream().filter(t -> t.getReceiveEventClass().isAssignableFrom(event)).forEach(t -> t.execute(event));
    }

}
