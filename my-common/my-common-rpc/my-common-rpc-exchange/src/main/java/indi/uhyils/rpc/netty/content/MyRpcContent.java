package indi.uhyils.rpc.netty.content;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 09时23分
 */
public class MyRpcContent {
    /**
     * rpc版本
     */
    public static final int VERSION = 1;
    /**
     * rpc标志
     */
    private static final int AGREEMENT_START_INT = 0x929d;
    /**
     * rpc标志,两字节占用
     */
    public static final byte[] AGREEMENT_START = new byte[]{
            (byte) (AGREEMENT_START_INT >> 8 & 0xff),
            (byte) (AGREEMENT_START_INT & 0xff)
    };

}
