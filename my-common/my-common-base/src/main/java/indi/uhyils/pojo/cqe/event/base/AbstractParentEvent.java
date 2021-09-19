package indi.uhyils.pojo.cqe.event.base;

import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;


/**
 * 父类事件
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 09时27分
 */
public abstract class AbstractParentEvent extends DefaultCQE implements BaseParentEvent {

    /**
     * 幂等号
     */
    private final Long sign;

    protected AbstractParentEvent() {
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        sign = bean.newId();
    }

    @Override
    public Long getSign() {
        return sign;
    }

    @Override
    public List<BaseEvent> transToBaseEvent() {
        List<BaseEvent> events = new ArrayList<>(transToBaseEvent0());
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
