package indi.uhyils.rpc.netty.pojo.request;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.AbstractRpcData;

/**
 * rpc请求体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时23分
 */
public abstract class RpcRequest extends AbstractRpcData {


    public RpcRequest(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
        this.type = RpcTypeEnum.REQUEST.getCode();
    }

    public RpcRequest() {
    }
}
