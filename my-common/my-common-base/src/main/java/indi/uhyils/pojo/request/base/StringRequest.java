package indi.uhyils.pojo.request.base;

/**
 * 字符串请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时33分
 */
public class StringRequest extends DefaultRequest {
    /**
     * 字符串
     */
    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
