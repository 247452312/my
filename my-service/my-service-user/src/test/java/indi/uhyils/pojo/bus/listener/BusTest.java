package indi.uhyils.pojo.bus.listener;

import java.util.function.Function;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月13日 09时42分
 */
public class BusTest {


    @Test
    public void testDoThing() {
        Function<Void, Void> ttt = (t) -> {
            System.out.println(11);
            return null;
        };

        Void apply = ttt.apply(null);
    }


    private void ttt() {

    }
}
