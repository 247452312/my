package indi.uhyils.util;

import com.alibaba.fastjson.JSON;
import indi.uhyils.core.message.Message;
import indi.uhyils.core.register.Register;
import indi.uhyils.netty.util.NettyChannelUtil;
import indi.uhyils.netty.util.NettyMessageUtil;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 22时16分
 * @Version 1.0
 */
public class PushUtil {

    public static Boolean push(Register register, Message message) {
        String channelId = register.getChannelId();
        // 有channelId的情况下
        if (StringUtils.isBlank(channelId)) {
            Channel channel = NettyChannelUtil.getChannel(channelId);
            channel.writeAndFlush(NettyMessageUtil.msgToByte(message));
        } else {
            RestTemplate bean = SpringUtil.getBean(RestTemplate.class);
            Object response = bean.postForObject(register.getUrl(), message, Object.class);
            if (LogUtil.isDebugEnabled(PushUtil.class)) {
                LogUtil.debug(PushUtil.class, "\r\n发送消息为:\r\n%s\r\n接收消息为:\r\n %s", JSON.toJSONString(message), JSON.toJSONString(response));
            }
        }
        return Boolean.TRUE;
    }
}
