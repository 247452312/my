package indi.uhyils.enums;

/**
 * 参数类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 10时28分
 */
public enum NodeParamTypeEnum {
    /**
     *
     */
    STRING(1),
    INT(2),
    ;

    private final Integer code;


    NodeParamTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
