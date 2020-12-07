package indi.uhyils.pojo.model;

import indi.uhyils.enum_.OrderNodeFieldSourceEnum;
import indi.uhyils.enum_.OrderNodeFieldTypeEnum;
import indi.uhyils.enum_.OrderNodeFieldValueTypeEnum;
import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderBaseNodeFieldEntity extends BaseVoEntity {

    /**
     * 节点id
     */
    private Long baseOrderId;

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
     * 字段类型{@link OrderNodeFieldTypeEnum}
     */
    private Integer type;

    /**
     * 是否可编辑
     */
    private Boolean edit;

    /**
     * 数值类型{@link OrderNodeFieldValueTypeEnum}
     */
    private Integer valueType;

    /**
     * 数据来源{@link OrderNodeFieldSourceEnum}
     */
    private Integer dataSources;

    /**
     * 如果是单选,多选,下拉框,那么具体的值去对应id中去找
     */
    private Long relationId;

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public Integer getDataSources() {
        return dataSources;
    }

    public void setDataSources(Integer dataSources) {
        this.dataSources = dataSources;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }
}
