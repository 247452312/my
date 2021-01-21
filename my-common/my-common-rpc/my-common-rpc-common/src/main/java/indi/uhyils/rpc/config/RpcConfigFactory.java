package indi.uhyils.rpc.config;

import indi.uhyils.rpc.constant.RpcConstant;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 20时03分
 */
public class RpcConfigFactory {

    public static RpcConfig newDefault() {
        RpcConfig rpcConfig = new RpcConfig();
        rpcConfig.getApplication().setName(RpcConstant.RPC_PREFIX);
        ProviderConfig provider = rpcConfig.getProvider();
        provider.setPort(8080);
        RegistryConfig registry = rpcConfig.getRegistry();
        registry.setHost("192.168.1.101");
        registry.setPort(8848);
        return rpcConfig;
    }

}
