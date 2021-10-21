package indi.uhyils.bus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import indi.uhyils.BaseTest;
import indi.uhyils.bus.model.AEvent;
import indi.uhyils.bus.model.BEvent;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.pojo.cqe.event.base.BaseParentEvent;
import indi.uhyils.pojo.cqe.event.base.PackageEvent;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月29日 20时41分
 */
public class BusTest extends BaseTest {

    @Autowired
    private BusInterface bus;

    @Test
    public void testSync() throws InterruptedException {
        PackageEvent packageEvent = new PackageEvent();
        TestParentEvent baseEvent = new TestParentEvent();
        packageEvent.setEvents(Collections.singletonList(baseEvent));
        bus.asyncCommitAndPush(packageEvent);
        Thread.sleep(3000L);
        Asserts.assertTrue(TestEvent.mark);

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
        Asserts.assertTrue(unique == 1L);
    }
}
