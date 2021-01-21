package indi.uhyils.rpc.config;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 21时37分
 */
public class RpcConfigWarehouse {

    /**
     * 唯一实例
     */
    private static RpcConfig config;

    public static RpcConfig getRpcConfig() {
        return config;
    }

    public static void setRpcConfig(RpcConfig config) {
        RpcConfigWarehouse.config = config;
    }
}
