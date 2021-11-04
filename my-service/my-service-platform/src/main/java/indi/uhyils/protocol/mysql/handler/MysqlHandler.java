package indi.uhyils.protocol.mysql.handler;

import indi.uhyils.protocol.mysql.enums.MysqlHandlerStatusEnum;
import io.netty.channel.ChannelInboundHandler;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时18分
 */
public interface MysqlHandler extends ChannelInboundHandler {

    /**
     * 获取此连接状态
     *
     * @return
     */
    MysqlHandlerStatusEnum getStatus();

    /**
     * 关闭
     */
    void closeOnFlush();
}
