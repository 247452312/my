package indi.uhyils.rpc.netty.core;

import indi.uhyils.annotation.NotNull;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.RpcNetty;

/**
 * rpc的客户端netty执行
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月30日 16时30分
 */
public interface RpcNettyConsumer extends RpcNetty {

    /**
     * 发送信息到服务端
     *
     * @param bytes 发送的信息
     *
     * @return 是否发送成功
     */
    boolean sendMsg(byte[] bytes);

    /**
     * 等待返回
     *
     * @param unique 请求的唯一标识
     *
     * @return 返回值
     */
    @NotNull
    RpcData wait(Long unique);

    /**
     * 服务端返回时使用,将返回值通知等待返回端
     *
     * @param rpcData 返回值的内容
     */
    void put(RpcData rpcData);
}
