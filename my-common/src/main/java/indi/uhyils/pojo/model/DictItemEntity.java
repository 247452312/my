package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseEntity;

/**
 * 字典项
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月16日 14时50分
 */
public class DictItemEntity extends BaseEntity {
    /**
     * 字典id
     */
    private String dictId;
    /**
     * 文本
     */
    private String text;
    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private String sortOrder;

    /**
     * 0->不启用 1->启用
     */
    private Integer status;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
