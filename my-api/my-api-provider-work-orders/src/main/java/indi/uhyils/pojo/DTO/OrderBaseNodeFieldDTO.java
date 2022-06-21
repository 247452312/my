package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 工单节点属性样例表(OrderBaseNodeField)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分02秒
 */
public class OrderBaseNodeFieldDTO extends IdDTO {

    private static final long serialVersionUID = 252870076764045788L;


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
    private Integer empty;

    /**
     * 是否可编辑
     */
    private Integer edit;

    /**
     * 字段类型(1->编辑框 2->单选框 3->多选框 4->下拉框 5->文本框)
     */
    private Integer type;

    /**
     * 数值类型 1->字符串 2->数值 3->只允许英文 4->email 5->日期)
     */
    private Integer valueType;

    /**
     * 数据来源
     */
    private Integer dataSources;

    /**
     * 如果是单选,多选,下拉框,那么具体的值关联id
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


    public Integer getEmpty() {
        return empty;
    }

    public void setEmpty(Integer empty) {
        this.empty = empty;
    }


    public Integer getEdit() {
        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
