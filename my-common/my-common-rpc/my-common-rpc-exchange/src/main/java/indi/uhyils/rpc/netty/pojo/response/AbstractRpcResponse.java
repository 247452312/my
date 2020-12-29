package indi.uhyils.rpc.netty.pojo.response;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.AbstractRpcData;

import java.nio.charset.StandardCharsets;

/**
 * rpc响应体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时23分
 */
public abstract class AbstractRpcResponse extends AbstractRpcData {


    public AbstractRpcResponse(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
        this.type = RpcTypeEnum.RESPONSE.getCode();
    }

    public AbstractRpcResponse() {
    }

}
