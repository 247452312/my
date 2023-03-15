package indi.uhyils.enums;

/**
 * 协议类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时24分
 */
public enum ProtocolTypeEnum {
    /**
     *
     */
    RPC(1),
    MQ(2),
    TASK(3),
    HTTP(4),
    ;

    private final Integer code;


    ProtocolTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
