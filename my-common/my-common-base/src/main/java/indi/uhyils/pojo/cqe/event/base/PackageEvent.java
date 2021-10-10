package indi.uhyils.pojo.cqe.event.base;

import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.List;

/**
 * 打包event
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月10日 15时54分
 */
public class PackageEvent extends AbstractParentEvent {

    private static final long serialVersionUID = -1L;

    /**
     * 打包的event,带有顺序
     */
    private List<BaseParentEvent> events;

    public List<BaseParentEvent> getEvents() {
        return events;
    }

    public void setEvents(List<BaseParentEvent> events) {
        this.events = events;
    }

    @Override
    protected List<BaseEvent> transToBaseEvent0() {
        Asserts.assertTrue(events != null, "事件不能为空");
        List<BaseEvent> result = new ArrayList<>();
        for (BaseParentEvent event : events) {
            result.addAll(event.transToBaseEvent());
        }
        return result;
    }
}
