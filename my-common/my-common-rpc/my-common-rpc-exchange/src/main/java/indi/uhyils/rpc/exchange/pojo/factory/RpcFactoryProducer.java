package indi.uhyils.rpc.exchange.pojo.factory;

import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.spi.RpcSpiManager;

import java.util.Map;

/**
 * Rpc工厂生成器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月19日 10时07分
 */
public class RpcFactoryProducer {

    /**
     * 默认的rpc请求工厂的spi名称
     */
    private static final String requestFactoryDefaultRpcSpiName = "default_rpc_request_factory";
    /**
     * 默认的rpc响应工厂的spi名称
     */
    private static final String responseFactoryDefaultRpcSpiName = "default_rpc_response_factory";

    /**
     * rpc请求工厂在rpc自定义扩展中的key
     */
    private static final String requestFactoryRpcSpiCustomKey = "rpc_request_factory";
    /**
     * rpc响应工厂在rpc自定义扩展中的key
     */
    private static final String responseFactoryRpcSpiCustomKey = "rpc_response_factory";

    public static RpcFactory build(RpcTypeEnum rpcTypeEnum) {
        RpcConfig instance = RpcConfigFactory.getInstance();
        Map<String, Object> custom = instance.getCustom().getCustom();
        switch (rpcTypeEnum) {
            case RESPONSE:
                return (RpcFactory) RpcSpiManager.getExtensionByClass(RpcFactory.class, (String) custom.getOrDefault(responseFactoryRpcSpiCustomKey, responseFactoryDefaultRpcSpiName));
            //default默认使用request的请求
            case REQUEST:
            default:
                return (RpcFactory) RpcSpiManager.getExtensionByClass(RpcFactory.class, (String) custom.getOrDefault(requestFactoryRpcSpiCustomKey, requestFactoryDefaultRpcSpiName));
        }
    }
}
