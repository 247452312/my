package indi.uhyils.util;


import com.alibaba.fastjson.JSONObject;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 14时10分
 */
public class AssertsTest {

    @Test
    public void assertException() {
        Asserts.assertException(() -> Asserts.assertException(() -> {
        }));
    }

    @Test
    public void testOptionToJson() {
        Optional<String> optionalS = Optional.ofNullable("asd");

        String s1 = optionalS.toString();
        System.out.println(s1);
        String str = JSONObject.toJSONString(optionalS);
        System.out.println(str);
        optionalS = Optional.ofNullable(null);
        String s = JSONObject.toJSONString(optionalS);
        System.out.println(s);
    }
}
