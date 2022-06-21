package indi.uhyils.util;

import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.pojo.cqe.event.base.BaseParentEvent;
import indi.uhyils.pojo.cqe.event.base.PackageEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 事件转换工具类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月10日 16时28分
 */
public final class EventUtil {


    /**
     * 解析事件中的父类事件,打包事件
     *
     * @param list
     *
     * @return
     */
    public static List<BaseEvent> trans(List<BaseEvent> list) {
        List<BaseEvent> result = new ArrayList<>();
        for (BaseEvent event : list) {
            if (event instanceof BaseParentEvent) {
                BaseParentEvent baseParentEvent = (BaseParentEvent) event;
                result.addAll(baseParentEvent.transToBaseEvent());
            } else {
                result.add(event);
            }
        }
        return result;
    }

    /**
     * 解析事件中的父类事件,打包事件
     *
     * @param event
     *
     * @return
     */
    public static List<BaseEvent> trans(BaseEvent event) {
        List<BaseEvent> result = new ArrayList<>();
        if (event instanceof BaseParentEvent) {
            BaseParentEvent baseParentEvent = (BaseParentEvent) event;
            result.addAll(baseParentEvent.transToBaseEvent());
        } else {
            result.add(event);
        }
        return result;
    }

    /**
     * 打包父类事件
     *
     * @param events
     *
     * @return
     */
    public static PackageEvent packageEvent(List<BaseParentEvent> events) {
        PackageEvent packageEvent = new PackageEvent();
        packageEvent.setEvents(events);
        return packageEvent;
    }

    /**
     * 打包父类事件
     *
     * @param events
     *
     * @return
     */
    public static PackageEvent packageEvent(BaseParentEvent... events) {
        List<BaseParentEvent> baseParentEvents = Arrays.asList(events);
        return packageEvent(baseParentEvents);
    }

}
