package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderNodeFieldEntity extends BaseVoEntity {

    /**
     * 数值类型 {@link indi.uhyils.enum_.NodeFieldValueTypeEnum}
     */
    private Integer valueType;

    /**
     * 是否可编辑
     */
    private Integer edit;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 字段类型{@link indi.uhyils.enum_.NodeFieldTypeEnum}
     */
    private Integer type;

    /**
     * 是否可以为空
     */
    private Integer empty;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 节点id
     */
    private String baseOrderId;

    /**
     * 属性备注
     */
    private String desc;


    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public Integer getEdit() {
        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEmpty() {
        return empty;
    }

    public void setEmpty(Integer empty) {
        this.empty = empty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(String baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
