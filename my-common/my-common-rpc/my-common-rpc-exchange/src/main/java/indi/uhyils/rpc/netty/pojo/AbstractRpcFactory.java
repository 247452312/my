package indi.uhyils.rpc.netty.pojo;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时07分
 */
public abstract class AbstractRpcFactory implements RpcFactory {

    @Override
    public RpcData createByByteBuf(ByteBuf in) throws RpcException, ClassNotFoundException {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        return createByBytes(bytes);
    }

    /**
     * 获取rpc的类型
     *
     * @return
     */
    protected abstract RpcTypeEnum getRpcType();

}
