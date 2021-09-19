package indi.uhyils.protocol.register;

import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import java.util.List;

/**
 * 事件注册者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 09时21分
 */
public interface Register {

    /**
     * 获取这个register监听的是哪一个事件
     *
     * @return
     */
    List<Class<? extends BaseEvent>> targetEvent();

    /**
     * 事件 coming!
     *
     * @param event
     */
    void onEvent(BaseEvent event);

}
