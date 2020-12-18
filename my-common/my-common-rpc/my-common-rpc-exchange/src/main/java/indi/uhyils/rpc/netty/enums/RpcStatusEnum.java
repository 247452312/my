package indi.uhyils.rpc.netty.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 09时53分
 */
public enum RpcStatusEnum {
    /**
     * 成功
     */
    OK(20),
    /**
     * 客户端超时
     */
    CLIENT_TIMEOUT(30),
    /**
     * 服务端超时
     */
    SERVER_TIMEOUT(31),
    /**
     * 请求失败
     */
    BAD_REQUEST(40),
    /**
     * 回应失败
     */
    BAD_RESPONSE(50),
    /**
     * 未找到接口
     */
    SERVICE_NOT_FOUND(60),
    /**
     * 接口错误
     */
    SERVICE_ERROR(70),
    /**
     * 服务端错误
     */
    SERVER_ERROR(80),
    /**
     * 客户端错误
     */
    CLIENT_ERROR(90),
    /**
     * 服务器线程池已耗尽错误
     */
    SERVER_THREADPOOL_EXHAUSTED_ERROR(100);

    RpcStatusEnum(Integer code) {
        this.code = code;
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
