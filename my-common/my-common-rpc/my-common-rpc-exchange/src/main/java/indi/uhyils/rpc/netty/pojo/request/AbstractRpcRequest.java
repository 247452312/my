package indi.uhyils.rpc.netty.pojo.request;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.AbstractRpcData;

import java.nio.charset.StandardCharsets;

/**
 * rpc请求体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时23分
 */
public abstract class AbstractRpcRequest extends AbstractRpcData {


    public AbstractRpcRequest(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
        this.type = RpcTypeEnum.REQUEST.getCode();
    }

    public AbstractRpcRequest() {
    }

    @Override
    public void dealOtherThing(Object o) {

    }

    @Override
    protected int getSizeStartIndex() {
        return 7;
    }

    @Override
    public byte[] toBytes() {
        byte[] previousBytes = new byte[7];
        previousBytes[0] = MyRpcContent.AGREEMENT_START[0];
        previousBytes[1] = MyRpcContent.AGREEMENT_START[1];
        previousBytes[2] = (byte) ((version << 2) + (type << 1));

        byte[] strBytes = getContentString().getBytes(StandardCharsets.UTF_8);
        int i = strBytes.length;
        //由高位到低位
        previousBytes[3] = (byte) ((i >> 24) & 0xFF);
        previousBytes[4] = (byte) ((i >> 16) & 0xFF);
        previousBytes[5] = (byte) ((i >> 8) & 0xFF);
        previousBytes[6] = (byte) (i & 0xFF);
        byte[] result = new byte[7 + strBytes.length];
        System.arraycopy(previousBytes, 0, result, 0, previousBytes.length);
        System.arraycopy(strBytes, 0, result, previousBytes.length, strBytes.length);

        return result;
    }
}
