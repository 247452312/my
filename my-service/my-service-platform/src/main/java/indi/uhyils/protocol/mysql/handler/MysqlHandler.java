package indi.uhyils.protocol.mysql.handler;

import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.protocol.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.protocol.mysql.pojo.entity.PrepareInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandler;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时18分
 */
public interface MysqlHandler extends ChannelInboundHandler {

    /**
     * 获取此连接状态,并添加状态
     *
     * @return
     */
    MysqlHandlerStatusEnum getAndIncrementStatus();

    /**
     * 获取此连接状态
     *
     * @return
     */
    MysqlHandlerStatusEnum getStatus();

    /**
     * 设置状态
     *
     * @param status
     */
    void setStatus(MysqlHandlerStatusEnum status);

    /**
     * 获取客户端发送过来的index序列
     *
     * @return
     */
    long index();

    /**
     * 修改index
     *
     * @param index
     */
    void changeIndex(long index);

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

    /**
     * 获取加密后的密码
     *
     * @return
     */
    byte[] getPassword();

    /**
     * 设置密码
     *
     * @param password
     */
    void setPassword(byte[] password);

    /**
     * 获取auth时使用index
     *
     * @return
     */
    byte getLoginIndex();

    /**
     * 获取客户端连接
     *
     * @return
     */
    Channel getClientChannel();

    String getDbName();

    void setDbName(String dbName);

    /**
     * 获取计划游标
     *
     * @return
     */
    Long getAndAddPlanIndex(Integer count);

    /**
     * 获取消费者信息
     *
     * @return
     */
    ConsumerInfoDTO getConsumerInfo();

    /**
     * 设置消费者信息
     *
     * @param consumerInfoDTO
     */
    void setConsumerInfo(ConsumerInfoDTO consumerInfoDTO);

}
