package indi.uhyils.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 10时14分
 */
public enum ProtocolTypeEnum {
    /**
     *
     */
    HTTP(1),
    MY_RPC(2),
    DB(3),
    MYSQL(4);

    private final Integer code;


    ProtocolTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
