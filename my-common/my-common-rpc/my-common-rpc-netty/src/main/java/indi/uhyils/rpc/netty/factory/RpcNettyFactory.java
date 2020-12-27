package indi.uhyils.rpc.netty.factory;

import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.consumer.RpcNettyNormalConsumer;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
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
    public static RpcNetty createNetty(RpcNettyTypeEnum type, NettyInitDto nettyInit, Long outTime) {
        switch (type) {
            case PROVIDER:
                return createProvider(nettyInit.getHost(), nettyInit.getPort(), nettyInit.getCallback(), outTime);
            case CONSUMER:
                return createConsumer(nettyInit.getHost(), nettyInit.getPort(), nettyInit.getCallback(), outTime);
            default:
                return null;
        }
    }

    /**
     * 创建一个netty
     *
     * @param type
     * @return
     */
    public static RpcNetty createNetty(RpcNettyTypeEnum type, NettyInitDto nettyInit) {
        return createNetty(type, nettyInit, 6000L);
    }

    /**
     * 创建一个服务消费者
     *
     * @param host
     * @param port
     * @return
     */
    private static RpcNetty createConsumer(String host, Integer port, RpcCallBack callBack, Long outTime) {
        RpcNetty rpcNettyNormalConsumer = new RpcNettyNormalConsumer(outTime, callBack);
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
    private static RpcNetty createProvider(String host, Integer port, RpcCallBack callback, Long outTime) {
        RpcNetty rpcNettyNormalProvider = new RpcNettyNormalProvider(outTime, callback);
        rpcNettyNormalProvider.init(host, port);
        return rpcNettyNormalProvider;
    }
}
