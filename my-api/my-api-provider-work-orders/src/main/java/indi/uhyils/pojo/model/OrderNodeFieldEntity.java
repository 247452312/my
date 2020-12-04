package indi.uhyils.pojo.model;

import indi.uhyils.enum_.OrderNodeFieldSourceEnum;
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
    private String baseOrderNodeId;

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
     * 字段类型{@link indi.uhyils.enum_.OrderNodeFieldTypeEnum}
     */
    private Integer type;

    /**
     * 数值类型 {@link indi.uhyils.enum_.OrderNodeFieldValueTypeEnum}
     */
    private Integer valueType;

    /**
     * 数据来源{@link OrderNodeFieldSourceEnum}
     */
    private Integer dataSources;

    /**
     * 如果是单选,多选,下拉框,那么具体的值去对应id中去找
     */
    private String relationId;


    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
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

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseOrderNodeId() {
        return baseOrderNodeId;
    }

    public void setBaseOrderNodeId(String baseOrderNodeId) {
        this.baseOrderNodeId = baseOrderNodeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getDataSources() {
        return dataSources;
    }

    public void setDataSources(Integer dataSources) {
        this.dataSources = dataSources;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
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
        return Objects.equals(baseOrderNodeId, that.baseOrderNodeId) && Objects.equals(name, that.name) && Objects.equals(desc, that.desc) && Objects.equals(defaultValue, that.defaultValue) && Objects.equals(empty, that.empty) && Objects.equals(edit, that.edit) && Objects.equals(type, that.type) && Objects.equals(valueType, that.valueType) && Objects.equals(dataSources, that.dataSources) && Objects.equals(relationId, that.relationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseOrderNodeId, name, desc, defaultValue, empty, edit, type, valueType, dataSources, relationId);
    }
}
