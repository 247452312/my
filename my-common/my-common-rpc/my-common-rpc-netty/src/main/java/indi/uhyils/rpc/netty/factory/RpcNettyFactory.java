package indi.uhyils.rpc.netty.factory;

import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.netty.callback.RpcRequestCallback;
import indi.uhyils.rpc.netty.consumer.RpcNettyNormalConsumer;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.provider.RpcNettyNormalProvider;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月20日 13时39分
 */
public class RpcNettyFactory {
    /**
     * 创建一个netty
     *
     * @param type
     * @return
     */
    public static RpcNetty createNetty(RpcNettyTypeEnum type, String host, Integer port, RpcRequestCallback callback) {
        switch (type) {
            case PROVIDER:
                return createProvider(host, port, callback);
            case CONSUMER:
                return createConsumer(host, port);
            default:
                return null;
        }
    }

    /**
     * 创建一个服务消费者
     *
     * @param host
     * @param port
     * @return
     */
    private static RpcNetty createConsumer(String host, Integer port) {
        RpcNetty rpcNettyNormalConsumer = new RpcNettyNormalConsumer();
        rpcNettyNormalConsumer.init(host, port);
        return rpcNettyNormalConsumer;
    }

    /**
     * 创建一个服务提供者
     *
     * @param host
     * @param port
     * @return
     */
    private static RpcNetty createProvider(String host, Integer port, RpcRequestCallback callback) {
        RpcNetty rpcNettyNormalProvider = new RpcNettyNormalProvider(callback);
        rpcNettyNormalProvider.init(host, port);
        return rpcNettyNormalProvider;
    }
}
