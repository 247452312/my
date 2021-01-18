package indi.uhyils.rpc.pojo.response;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.pojo.AbstractRpcData;

/**
 * rpc响应体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时23分
 */
public abstract class AbstractRpcResponse extends AbstractRpcData {


    protected AbstractRpcResponse(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
        this.type = RpcTypeEnum.RESPONSE.getCode();
    }

    protected AbstractRpcResponse() {
    }

}
