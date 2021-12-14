package indi.uhyils.bus.model;


import indi.uhyils.pojo.cqe.event.base.AbstractParentEvent;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月10日 15时26分
 */
public class BEvent extends AbstractParentEvent {


    @Override
    protected List<BaseEvent> transToBaseEvent0() {
        return null;
    }
}
