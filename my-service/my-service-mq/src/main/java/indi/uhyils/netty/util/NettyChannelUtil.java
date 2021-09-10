package indi.uhyils.netty.util;

import io.netty.channel.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储netty的channel的地方
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月06日 08时15分
 */
public class NettyChannelUtil {

    /**
     * 保存channel的地方
     * key: channelId
     * map: netty
     */
    private static final Map<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();

    /**
     * 添加channel
     *
     * @param channel 长连接
     *
     * @return
     */
    public static Boolean addChannel(Channel channel) {
        if (!channel.isActive()) {
            return Boolean.FALSE;
        }
        String channelId = channel.id().asLongText();
        CHANNEL_MAP.put(channelId, channel);
        return Boolean.TRUE;

    }

    /**
     * 添加channel
     *
     * @param channel 长连接
     *
     * @return
     */
    public static Boolean addChannelIfNoContains(Channel channel) {
        String id = channel.id().asLongText();
        if (CHANNEL_MAP.containsKey(id)) {
            return Boolean.FALSE;
        }
        return addChannel(channel);
    }

    /**
     * 获取channel
     *
     * @param channelId
     *
     * @return
     */
    public static Channel getChannel(String channelId) {
        return CHANNEL_MAP.get(channelId);
    }

    /**
     * 删除channel
     *
     * @param channelId
     *
     * @return
     */
    public static Channel removeChannel(String channelId) {
        return CHANNEL_MAP.remove(channelId);
    }


}
