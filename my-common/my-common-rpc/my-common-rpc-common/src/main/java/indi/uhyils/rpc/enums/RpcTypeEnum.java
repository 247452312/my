package indi.uhyils.rpc.enums;

/**
 * 请求响应编码
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时34分
 */
public enum RpcTypeEnum {
    /**
     * 响应
     */
    RESPONSE(0),
    /**
     * 请求
     */
    REQUEST(1);

    private Integer code;

    RpcTypeEnum(Integer code) {
        this.code = code;
    }

    public static RpcTypeEnum parse(Integer code) {
        switch (code) {
            case 1:
                return REQUEST;
            case 0:
                return RESPONSE;
            default:
                return null;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
