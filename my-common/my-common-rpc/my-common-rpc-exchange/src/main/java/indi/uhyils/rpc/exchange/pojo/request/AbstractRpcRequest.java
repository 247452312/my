package indi.uhyils.rpc.exchange.pojo.request;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.AbstractRpcData;

/**
 * rpc请求体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时23分
 */
public abstract class AbstractRpcRequest extends AbstractRpcData {


    protected AbstractRpcRequest(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
        this.type = RpcTypeEnum.REQUEST.getCode();
    }

    protected AbstractRpcRequest() {
    }

}
