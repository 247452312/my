package indi.uhyils.bus;

import indi.uhyils.pojo.cqe.event.base.AbstractEvent;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月29日 20时43分
 */
public class TestAEvent extends AbstractEvent {

    public volatile static boolean mark = false;
}
