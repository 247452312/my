package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.netty.exception.RpcException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
public class ProviderRegistry<T> extends AbstractRegistry<T> {

    public ProviderRegistry(Cluster cluster, Class<T> serviceClass) {
        super(cluster, serviceClass);
    }

    @Override
    public String invoke(Long unique, String methodName, Class[] paramType, Object[] args) throws RpcException, ClassNotFoundException, InterruptedException {
        return null;
    }
}
