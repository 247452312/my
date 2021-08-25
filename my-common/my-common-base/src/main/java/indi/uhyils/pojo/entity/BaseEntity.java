package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.event.Event;
import java.io.Serializable;

/**
 * entity基础
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 15时38分
 */
public interface BaseEntity extends Serializable {

    /**
     * 是否可以修改
     *
     * @return
     */
    boolean canUpdate();

    /**
     * 添加触发事件
     *
     * @param event 事件
     */
    void addEvent(Event event);

    /**
     * 执行剩下的所有event
     */
    void executeEvent();
}
