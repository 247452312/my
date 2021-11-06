package indi.uhyils.protocol.mysql.handler;

import indi.uhyils.protocol.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.protocol.mysql.pojo.entity.PrepareInfo;
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

    /**
     * 获取错误数量
     *
     * @return
     */
    int getWarnCount();

    /**
     * 添加一次错误数量
     */
    void warnCountAdd();

    /**
     * 获取预处理sql
     *
     * @return
     */
    PrepareInfo getPrepareSql();

    /**
     * 获取预处理sql
     *
     * @return
     */
    PrepareInfo getPrepareSql(long prepareId);

    /**
     * 设置预处理的sql
     *
     * @param sql
     */
    long setPrepareSql(String sql);
}
