package indi.uhyils.pojo.cqe.event.base;

import java.util.List;

/**
 * 父类事件,也是子类事件的一种
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 09时27分
 */
public interface BaseParentEvent extends BaseEvent {

    /**
     * 转化为子类事件
     *
     * @return
     */
    List<BaseEvent> transToBaseEvent();
}
