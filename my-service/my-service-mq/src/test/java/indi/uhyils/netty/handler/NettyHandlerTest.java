package indi.uhyils.netty.handler;

import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.finder.http.HttpProFinder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.netty.util.IpUtil;
import indi.uhyils.pojo.request.SendMessageRequest;
import indi.uhyils.util.SpringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月24日 12时39分
 * @Version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        IpUtil.class,
        ServiceExecuteHandler.class,
        SpringUtil.class
})
public class NettyHandlerTest {


    @Test
    public void testHttp() throws Exception {
        String s =
                "POST /test HTTP/1.1\r\n" +
                        "Host: localhost:8080\r\n" +
                        "Authorization: Bearer 9cf2c3cb-3d44-4618-9805-e62da8eb18f9\r\n" +
                        "Content-Type: application/json\r\n" +
                        "Content-Length: 285\r\n" +
                        "\r\n" +
                        "{\r\n" +
                        "    \"methodName\": \"sendMessage\",\r\n" +
                        "    \"paramsType\": [\r\n" +
                        "        \"indi.uhyils.pojo.request.SendMessageRequest\"\r\n" +
                        "    ],\r\n" +
                        "    \"data\": [\r\n" +
                        "        {\r\n" +
                        "            \"data\": {},\r\n" +
                        "            \"key\": \"key\",\r\n" +
                        "            \"topic\": \"defualt\",\r\n" +
                        "            \"type\": \"NORMAL_MSG\"\r\n" +
                        "        }\r\n" +
                        "    ]\r\n" +
                        "}";
        MqByteToMessageDecoder decoder = new MqByteToMessageDecoder();
        Field finders = decoder.getClass().getDeclaredField("finders");
        finders.setAccessible(true);
        ArrayList<Finder> value = new ArrayList<>();
        mockStatic(IpUtil.class);
        when(IpUtil.getAddressStr(Mockito.any())).thenReturn("127.0.0.1");
        HttpProFinder finder = new HttpProFinder();
        value.add(finder);
        finders.set(decoder, value);
        ArrayList<Object> out = new ArrayList<>();
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(s.getBytes(StandardCharsets.UTF_8));
        ChannelHandlerContext mock = mock(ChannelHandlerContext.class);

        decoder.decode(mock, buffer, out);
        ProtocolParsingModel model = (ProtocolParsingModel) out.get(0);
        Assert.assertNotEquals(model, null);
        String ip = model.getIp();
        Assert.assertEquals(ip, "127.0.0.1");
        String methodName = model.getMethodName();
        Assert.assertEquals(methodName, "sendMessage");
        Class aClass = model.getParamsType()[0];
        Assert.assertEquals(aClass, SendMessageRequest.class);

    }
}
