package indi.uhyils.rpc.netty.pojo.response;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.AbstractRpcData;

import java.nio.charset.StandardCharsets;

/**
 * rpc响应体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时23分
 */
public abstract class RpcResponse extends AbstractRpcData {

    /**
     * 状态
     */
    protected byte status;

    public RpcResponse(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
        this.type = RpcTypeEnum.RESPONSE.getCode();
    }

    public RpcResponse() {
    }

    @Override
    protected int getSizeStartIndex() {
        return 8;
    }

    @Override
    public void dealOtherThing(Object o) {
        byte[] data = (byte[]) o;
        this.status = data[3];
    }

    public byte getStatus() {
        return status;
    }

    public abstract void setStatus(byte status);

    @Override
    public byte[] toBytes() {
        byte[] previousBytes = new byte[8];
        previousBytes[0] = MyRpcContent.AGREEMENT_START[0];
        previousBytes[1] = MyRpcContent.AGREEMENT_START[1];
        previousBytes[2] = (byte) ((version << 2) + (type << 1));
        previousBytes[3] = status;
        byte[] strBytes = getContentString().getBytes(StandardCharsets.UTF_8);
        int i = strBytes.length;
        //由高位到低位
        previousBytes[4] = (byte) ((i >> 24) & 0xFF);
        previousBytes[5] = (byte) ((i >> 16) & 0xFF);
        previousBytes[6] = (byte) ((i >> 8) & 0xFF);
        previousBytes[7] = (byte) (i & 0xFF);
        byte[] result = new byte[previousBytes.length + strBytes.length];
        System.arraycopy(previousBytes, 0, result, 0, previousBytes.length);
        System.arraycopy(strBytes, 0, result, previousBytes.length, strBytes.length);
        return result;
    }
}
