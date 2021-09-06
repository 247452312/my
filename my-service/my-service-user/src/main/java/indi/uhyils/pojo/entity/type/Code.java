package indi.uhyils.pojo.entity.type;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月06日 13时46分
 */
public class Code implements BaseType {

    private String code;

    public Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
