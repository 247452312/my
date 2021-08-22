package indi.uhyils.enum_;

/**
 * order符号枚举
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 18时06分
 */
public enum OrderSymbolEnum {

    /**
     * order的符号
     */
    ASC("asc"),
    DESC("desc");


    private final String code;

    OrderSymbolEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
