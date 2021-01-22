package indi.uhyils.rpc.netty;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.Channel;

/**
 * rpc调用中的netty
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月20日 13时42分
 */
public interface RpcNetty {

    /**
     * 获取bootStrap
     *
     * @return
     */
    AbstractBootstrap<?, ? extends Channel> getBootstrap();

    /**
     * 设置bootstrap
     *
     * @param bootstrap
     */
    void setBootstrap(AbstractBootstrap<?, ? extends Channel> bootstrap);

    /**
     * 初始化,其中host只有客户端有用
     *
     * @param host
     * @param port
     * @return
     */
    Boolean init(String host, Integer port);

    /**
     * 关闭,不会立即关闭.会等待线程结束
     *
     * @return
     */
    Boolean shutdown();

    /**
     * 发送信息 等待回应
     *
     * @param rpcData
     * @return
     */
    RpcData sendMsg(RpcData rpcData) throws RpcException, ClassNotFoundException, InterruptedException;
}
