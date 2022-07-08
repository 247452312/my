package indi.uhyils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import indi.uhyils.bus.BusInterface;
import indi.uhyils.bus.model.AEvent;
import indi.uhyils.bus.model.BEvent;
import indi.uhyils.pojo.cqe.event.base.AbstractEvent;
import indi.uhyils.pojo.cqe.event.base.AbstractParentEvent;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.pojo.cqe.event.base.BaseParentEvent;
import indi.uhyils.pojo.cqe.event.base.PackageEvent;
import indi.uhyils.protocol.register.TestEvent;
import indi.uhyils.protocol.register.TestEventRegister;
import indi.uhyils.protocol.register.TestParentEvent;
import indi.uhyils.protocol.register.TestParentEventBlank;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 10时19分
 */
public class BusTest extends BaseTest {

    @Autowired
    public BusInterface bus;

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
        TestEventRegister.b = false;
        bus.commit(new TestParentEvent());
        bus.pushEvent();
        Asserts.assertTrue(TestEventRegister.b);
        bus.commit(new TestParentEventBlank());
        bus.pushEvent();
        bus.cleanCommitEvent();
    }

    @Test
    public void commitAndPush() {
        TestEventRegister.b = false;
        Asserts.assertTrue(!TestEventRegister.b);
        bus.commitAndPush(new TestParentEvent());
        Asserts.assertTrue(TestEventRegister.b);
        bus.cleanCommitEvent();
    }

    @Test
    public void remove() {
        bus.commit(new TestParentEvent());
        List<BaseEvent> baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 2);
        bus.remove(AbstractEvent.class);
        baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);
        baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 0);

        bus.cleanCommitEvent();

    }

    @Test
    public void preciseRemove() {
        bus.commit(new TestParentEvent());

        // 添加进去两个 一个父类一个子类
        List<BaseEvent> baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 2);
        baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 2);
        baseEvents = bus.get(AbstractParentEvent.class);
        Asserts.assertTrue(baseEvents.size() == 1);

        // 精确移除, 这一行移除不到东西
        bus.preciseRemove(AbstractEvent.class);
        baseEvents = bus.get(BaseEvent.class);
        Asserts.assertTrue(baseEvents.size() == 2);
        baseEvents = bus.get(AbstractEvent.class);
        Asserts.assertTrue(baseEvents.size() == 2);
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
        bus.cleanCommitEvent();
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
        bus.cleanCommitEvent();

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
        bus.cleanCommitEvent();

    }

    @Test
    public void testJSON() {
        PackageEvent list = new PackageEvent();
        List<BaseParentEvent> event = new ArrayList<>();
        AEvent e = new AEvent();
        e.setUnique(1L);
        event.add(e);
        BEvent b = new BEvent();
        b.setUnique(2L);
        event.add(b);
        list.setEvents(event);

        String s = JSON.toJSONString(list, SerializerFeature.WriteClassName);
        System.out.println("fastJSON解析: " + s);

        BaseEvent listEvent_ = JSON.parseObject(s, BaseEvent.class);
        PackageEvent listEvent = (PackageEvent) listEvent_;
        List<BaseParentEvent> targetEvents = listEvent.getEvents();
        BaseEvent aEvent = targetEvents.get(0);
        Long unique = aEvent.getUnique();
        Asserts.assertTrue(unique == 1L || unique == 2L);
        bus.cleanCommitEvent();
    }

}
