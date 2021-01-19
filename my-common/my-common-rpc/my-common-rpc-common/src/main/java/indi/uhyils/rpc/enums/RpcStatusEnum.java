package indi.uhyils.rpc.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 09时53分
 */
public enum RpcStatusEnum {
    /**
     * 无
     */
    NULL("无",(byte) 0),
    /**
     * 成功
     */
    OK("成功",(byte) 20),
    /**
     * 客户端超时
     */
    CLIENT_TIMEOUT("客户端超时",(byte) 30),
    /**
     * 服务端超时
     */
    SERVER_TIMEOUT("服务端超时",(byte) 31),
    /**
     * 请求失败
     */
    BAD_REQUEST("请求失败",(byte) 40),
    /**
     * 回应失败
     */
    BAD_RESPONSE("回应失败",(byte) 50),
    /**
     * 未找到接口
     */
    SERVICE_NOT_FOUND("未找到接口",(byte) 60),
    /**
     * 接口错误
     */
    SERVICE_ERROR("接口错误",(byte) 70),
    /**
     * 生产者错误
     */
    PROVIDER_ERROR("生产者错误",(byte) 80),
    /**
     * 消费者错误
     */
    CONSUMER_ERROR("消费者错误",(byte) 90),
    /**
     * 服务器线程池已耗尽错误
     */
    SERVER_THREADPOOL_EXHAUSTED_ERROR("服务器线程池已耗尽错误",(byte) 100);

    private String name;
    private byte code;


    RpcStatusEnum(String name, byte code) {
        this.name = name;
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
