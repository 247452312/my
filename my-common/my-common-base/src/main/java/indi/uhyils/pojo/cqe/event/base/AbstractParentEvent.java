package indi.uhyils.pojo.cqe.event.base;

import java.util.ArrayList;
import java.util.List;


/**
 * 父类事件
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 09时27分
 */
public abstract class AbstractParentEvent extends AbstractEvent implements BaseParentEvent {


    @Override
    public List<BaseEvent> transToBaseEvent() {
        List<BaseEvent> baseEvents = transToBaseEvent0();
        if (baseEvents == null) {
            baseEvents = new ArrayList<>();
        }
        List<BaseEvent> events = new ArrayList<>(baseEvents);
        events.add(this);
        return events;
    }

    /**
     * 转化为需要发布的子事件
     *
     * @return
     */
    protected abstract List<BaseEvent> transToBaseEvent0();
}
