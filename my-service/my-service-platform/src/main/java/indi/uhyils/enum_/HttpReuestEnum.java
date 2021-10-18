package indi.uhyils.enum_;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 20时25分
 */
public enum HttpReuestEnum {
    /**
     * code
     */
    GET("get"),
    POST("post");

    private  final String code;

    HttpReuestEnum(String code) {
        this.code = code;
    }

    public static HttpReuestEnum parse(String requestType) {
        for (HttpReuestEnum value : values()) {
            if (Objects.equals(value.getCode(), requestType)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
