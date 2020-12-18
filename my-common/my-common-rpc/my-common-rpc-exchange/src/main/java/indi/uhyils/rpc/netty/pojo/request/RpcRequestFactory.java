package indi.uhyils.rpc.netty.pojo.request;

import indi.uhyils.rpc.netty.exception.ContentArrayQuantityMismatchException;
import indi.uhyils.rpc.netty.exception.NoMyRpcException;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.exception.VersionNotSupportedException;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时38分
 */
public class RpcRequestFactory implements RpcFactory {

    @Override
    public RpcData createByBytes(byte[] data) throws RpcException, ClassNotFoundException {
        return new RpcNormalRequest(data);
    }

    private RpcRequestFactory() {
    }

    public volatile static RpcFactory instance;

    public static RpcFactory getInstance() {
        if (null == instance) {
            synchronized (RpcRequestFactory.class) {
                if (null == instance) {
                    instance = new RpcRequestFactory();
                }
            }
        }
        return instance;
    }
}
