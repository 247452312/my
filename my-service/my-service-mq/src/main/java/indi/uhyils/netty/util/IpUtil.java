package indi.uhyils.netty.util;

import io.netty.channel.ChannelHandlerContext;
import java.net.InetSocketAddress;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月27日 09时29分
 */
public class IpUtil {

    /**
     * 获取请求者的ip
     *
     * @param ctx
     *
     * @return
     */
    public static String getAddressStr(ChannelHandlerContext ctx) {
        InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
        return socket.getAddress().getHostAddress();
    }
}
