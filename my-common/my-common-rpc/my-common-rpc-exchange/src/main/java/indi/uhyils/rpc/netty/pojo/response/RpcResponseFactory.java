package indi.uhyils.rpc.netty.pojo.response;

import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时47分
 */
public class RpcResponseFactory implements RpcFactory {
    @Override
    public RpcData createByBytes(byte[] data) throws RpcException, ClassNotFoundException {
        return new RpcNormalResponse(data);
    }

    public volatile static RpcFactory instance;

    public static RpcFactory getInstance() {
        if (null == instance) {
            synchronized (RpcResponseFactory.class) {
                if (null == instance) {
                    instance = new RpcResponseFactory();
                }
            }
        }
        return instance;
    }
}
