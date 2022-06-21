package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.BaseDTO;

/**
 * 工单节点属性中下拉选项
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月04日 15时00分
 */
public class OrderFieldSelectItemDTO implements BaseDTO {

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
