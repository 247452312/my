package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderNodeFieldEntity extends BaseVoEntity {

    /**
     * 节点id
     */
    private String baseOrderId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性备注
     */
    private String desc;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是否可以为空
     */
    private Boolean empty;

    /**
     * 是否可编辑
     */
    private Boolean edit;

    /**
     * 字段类型{@link indi.uhyils.enum_.NodeFieldTypeEnum}
     */
    private Integer type;

    /**
     * 数值类型 {@link indi.uhyils.enum_.NodeFieldValueTypeEnum}
     */
    private Integer valueType;


    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
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

    public Boolean getEmpty() {
        return empty;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OrderNodeFieldEntity that = (OrderNodeFieldEntity) o;
        return Objects.equals(getId(), that.getId());

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseOrderId, name, desc, defaultValue, empty, edit, type, valueType);
    }
}
