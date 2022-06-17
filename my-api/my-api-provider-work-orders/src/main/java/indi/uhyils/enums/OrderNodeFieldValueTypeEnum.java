package indi.uhyils.enums;

/**
 * 节点属性值类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时32分
 */
public enum OrderNodeFieldValueTypeEnum {
    /**
     * 同name
     */
    STRING("字符串", 1),
    VALUE("数值", 2),
    ENGLISH("只允许英文", 3),
    EMAIL("email", 4),
    DATE("日期", 5);


    private String name;

    private Integer code;

    OrderNodeFieldValueTypeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static OrderNodeFieldValueTypeEnum parse(Integer code) {
        switch (code) {

            case 2:
                return VALUE;
            case 3:
                return ENGLISH;
            case 4:
                return EMAIL;
            case 5:
                return DATE;
            case 1:
            default:
                return STRING;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
