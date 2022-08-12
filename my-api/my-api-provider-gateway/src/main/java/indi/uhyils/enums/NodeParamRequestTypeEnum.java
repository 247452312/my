package indi.uhyils.enums;

/**
 * 节点参数类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 10时25分
 */
public enum NodeParamRequestTypeEnum {
    /**
     *
     */
    RESPONSE(1, "出参"),
    REQUEST(2, "入参"),
    ;

    private final Integer code;

    private final String name;

    NodeParamRequestTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
