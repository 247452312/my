package indi.uhyils.pojo.DTO.response;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月04日 15时00分
 */
public class OrderFieldSelectItem implements Serializable {

    /**
     * select中的name
     */
    private String name;

    /**
     * select中的value
     */
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
