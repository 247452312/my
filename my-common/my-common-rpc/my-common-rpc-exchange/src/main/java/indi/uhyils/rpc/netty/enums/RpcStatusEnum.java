package indi.uhyils.rpc.netty.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 09时53分
 */
public enum RpcStatusEnum {
    /**
     * 成功
     */
    OK((byte) 20),
    /**
     * 客户端超时
     */
    CLIENT_TIMEOUT((byte) 30),
    /**
     * 服务端超时
     */
    SERVER_TIMEOUT((byte) 31),
    /**
     * 请求失败
     */
    BAD_REQUEST((byte) 40),
    /**
     * 回应失败
     */
    BAD_RESPONSE((byte) 50),
    /**
     * 未找到接口
     */
    SERVICE_NOT_FOUND((byte) 60),
    /**
     * 接口错误
     */
    SERVICE_ERROR((byte) 70),
    /**
     * 服务端错误
     */
    SERVER_ERROR((byte) 80),
    /**
     * 客户端错误
     */
    CLIENT_ERROR((byte) 90),
    /**
     * 服务器线程池已耗尽错误
     */
    SERVER_THREADPOOL_EXHAUSTED_ERROR((byte) 100);

    private byte code;

    RpcStatusEnum(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }
}
