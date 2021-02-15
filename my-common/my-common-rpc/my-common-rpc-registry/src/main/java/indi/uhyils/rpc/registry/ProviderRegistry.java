package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.registry.mode.RegistryMode;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
public class ProviderRegistry<T> extends AbstractRegistry<T> {

    public ProviderRegistry(Cluster cluster, Class<T> serviceClass, RegistryMode mode) {
        super(cluster, serviceClass);
        this.mode = mode;
        mode.setType(RpcNettyTypeEnum.PROVIDER);
    }

    @Override
    public String invoke(Long unique, String methodName, Class[] paramType, Object[] args) throws RpcException, ClassNotFoundException, InterruptedException {
        return null;
    }
}
