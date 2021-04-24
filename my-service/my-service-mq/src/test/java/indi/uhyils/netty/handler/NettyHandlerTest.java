package indi.uhyils.netty.handler;

import com.alibaba.fastjson.JSON;
import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月24日 12时39分
 * @Version 1.0
 */
public class NettyHandlerTest {

    @Test
    public void testHttp() throws Exception {
        String s =
            "POST /test HTTP/1.1\r\nAuthorization: Bearer 9cf2c3cb-3d44-4618-9805-e62da8eb18f9\r\nContent-Type: application/json\r\nUser-Agent: PostmanRuntime/7.26.8\r\nAccept: */*\r\nCache-Control: no-cache\r\nPostman-Token: d1f6ef45-764b-4ce0-8a0e-38ae0849d085\r\nHost: localhost:8080\r\nAccept-Encoding: gzip, deflate, br\r\nConnection: keep-alive\r\nContent-Length: 23\r\n\r\n{\"name\"=\"123\",\"age\"=22}";
        MqByteToMessageDecoder decoder = new MqByteToMessageDecoder();
        Field finders = decoder.getClass().getField("finders");
        finders.setAccessible(true);
        ArrayList<Finder> value = new ArrayList<>();
        finders.set(decoder, value);
        ArrayList<Object> out = new ArrayList<>();
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(s.getBytes(StandardCharsets.UTF_8));
        decoder.decode(null, buffer, out);
        for (Object o : out) {
            ProtocolParsingModel model = (ProtocolParsingModel)o;
            System.out.println(JSON.toJSONString(model));
        }
        
    }
}