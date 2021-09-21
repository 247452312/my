package indi.uhyils.bus;

import indi.uhyils.BaseTest;
import indi.uhyils.pojo.cqe.event.base.AbstractEvent;
import indi.uhyils.pojo.cqe.event.base.AbstractParentEvent;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.protocol.register.TestEvent;
import indi.uhyils.protocol.register.TestEventRegister;
import indi.uhyils.protocol.register.TestParentEvent;
import indi.uhyils.protocol.register.TestParentEventBlank;
import indi.uhyils.util.Asserts;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 10时19分
 */
public class BusTest extends BaseTest {

    @Autowired
    public Bus bus;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void pushEvent() {
        Asserts.assertTrue(!TestEventRegister.b);
        bus.commit(new TestParentEvent());
        bus.pushEvent();
        Asserts.assertTrue(TestEventRegister.b);
        bus.commit(new TestParentEventBlank());
        bus.pushEvent();
    }

    @Test
    public void commitAndPush() {
        TestEventRegister.b = false;
        Asserts.assertTrue(!TestEventRegister.b);
        bus.commitAndPush(new TestParentEvent());
        Asserts.assertTrue(TestEventRegister.b);
    }

    @Test
    public void remove() {
        bus.commit(new TestParentEvent());
        List<BaseEvent> baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);
        bus.remove(AbstractEvent.class);
        baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);
        baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);

        baseEvents = bus.get(AbstractParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);
        bus.remove(AbstractParentEvent.class);
        baseEvents = bus.get(AbstractParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);
        baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);

    }

    @Test
    public void preciseRemove() {
        bus.commit(new TestParentEvent());

        // 添加进去两个 一个父类一个子类
        List<BaseEvent> baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 2);
        baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);
        baseEvents = bus.get(AbstractParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);

        // 精确移除, 这一行移除不到东西
        bus.preciseRemove(AbstractEvent.class);
        baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 2);
        baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);
        baseEvents = bus.get(AbstractParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);

        // 精确移除父类
        bus.preciseRemove(TestParentEvent.class);
        baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);
        baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);

        // 精确移除子类
        bus.preciseRemove(TestEvent.class);
        baseEvents = bus.get(AbstractParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);
        baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);
    }

    @Test
    public void preciseGet() {
        bus.commit(new TestParentEvent());

        List<BaseEvent> baseEvents = bus.preciseGet(TestParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);
        baseEvents = bus.preciseGet(TestEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);
        baseEvents = bus.preciseGet(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);

    }

    @Test
    public void cleanCommitEvent() {
        bus.commit(new TestParentEvent());
        bus.cleanCommitEvent();

        List<BaseEvent> baseEvents = bus.preciseGet(TestParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);
        baseEvents = bus.preciseGet(TestEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);
        baseEvents = bus.preciseGet(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);

    }

}
