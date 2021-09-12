package indi.uhyils.pojo.bus.listener;

import indi.uhyils.BaseTest;
import indi.uhyils.bus.BusEventManager;
import indi.uhyils.pojo.cqe.event.BlankEvent;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月12日 18时13分
 */
public class EventListenerTest extends BaseTest {


    @org.junit.Test
    public void getReceiveEventClass() {

    }

    @org.junit.Test
    public void execute() {
        BusEventManager.push(new BlankEvent());
    }
}