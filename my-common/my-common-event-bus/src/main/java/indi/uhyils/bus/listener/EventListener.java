package indi.uhyils.bus.listener;

import indi.uhyils.pojo.cqe.event.BaseEvent;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 监听者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月12日 17时57分
 */
public interface EventListener<T extends BaseEvent> {

    /**
     * 获取监听的是那个事件
     *
     * @return
     */
    default Class<T> getReceiveEventClass() {
        Type superClass = this.getClass().getGenericInterfaces()[0];
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        return (Class<T>) type;
    }

    /**
     * 执行事件
     */
    void execute(T event);

    /**
     * 执行事件(不需要携带信息)
     */
    void execute(Class<T> event);

    /**
     * 是否跨应用
     *
     * @return
     */
    default boolean isCrossApp() {
        return false;
    }

}
