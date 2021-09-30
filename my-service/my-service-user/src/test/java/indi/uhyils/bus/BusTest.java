package indi.uhyils.bus;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.BaseTest;
import indi.uhyils.util.Asserts;
import java.lang.reflect.Type;
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
        bus.syncCommitAndPush(new TestParentEvent());
        Thread.sleep(3000L);
        Asserts.assertTrue(TestEvent.mark);

    }

    @Test
    public void testEventJson() throws ClassNotFoundException {
        String msg = "{\"sign\":1712285201464296768,\"user\":{\"id\":0,\"username\":\"admin\"},\"class\":\"indi.uhyils.bus.TestEvent\"}";
        Class<TestEvent> aClass = (Class<TestEvent>) Class.forName("indi.uhyils.bus.TestEvent");
        TestEvent o = JSONObject.parseObject(msg, (Type) aClass);
    }
}
