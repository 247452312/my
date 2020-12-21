package indi.uhyils.rpc.netty.pojo;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.util.BytesUtils;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时07分
 */
public abstract class AbstractRpcFactory implements RpcFactory {
    /**
     * response和request的标准RPC头大小
     */
    private static final Map<RpcTypeEnum, Integer> RPC_HEADER_SIZE;

    static {
        RPC_HEADER_SIZE = new HashMap<>(2);
        RPC_HEADER_SIZE.put(RpcTypeEnum.REQUEST, 7);
        RPC_HEADER_SIZE.put(RpcTypeEnum.RESPONSE, 8);
    }

    @Override
    public RpcData createByByteBuf(ByteBuf in) throws RpcException, ClassNotFoundException {
        RpcTypeEnum rpcType = getRpcType();
        Integer headLength = RPC_HEADER_SIZE.get(rpcType);
        byte[] lastBytes = new byte[headLength];
        byte[] startMsg = BytesUtils.getRpcHeadByByteBuf(in, headLength);
        if (startMsg == null) {
            return null;
        }
        System.arraycopy(startMsg, startMsg.length, lastBytes, 0, startMsg.length);
        byte[] headByteExceptStartMsg = in.readBytes(headLength - 2).array();
        System.arraycopy(headByteExceptStartMsg, headByteExceptStartMsg.length, lastBytes, startMsg.length, headByteExceptStartMsg.length);
        //取后四位组成一个int
        int size = lastBytes[--headLength] + (lastBytes[--headLength] << 8) + (lastBytes[--headLength] << 16) + (lastBytes[--headLength] << 24);
        ByteBuf contentByteBuf = in.readBytes(size);
        byte[] array = contentByteBuf.array();
        // 合并消息头和消息体
        byte[] data = BytesUtils.concat(lastBytes, array);
        // 回收已读字节
        in.discardReadBytes();
        return createByBytes(data);
    }

    /**
     * 获取rpc的类型
     *
     * @return
     */
    protected abstract RpcTypeEnum getRpcType();

}
