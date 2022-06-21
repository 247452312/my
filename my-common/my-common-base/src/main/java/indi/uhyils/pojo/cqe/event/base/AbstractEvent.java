package indi.uhyils.pojo.cqe.event.base;

import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.SpringUtil;

/**
 * 抽象事件
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时24分
 */
public abstract class AbstractEvent extends DefaultCQE implements BaseEvent {


    protected AbstractEvent() {
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        setUnique(bean.newId());
    }


}
