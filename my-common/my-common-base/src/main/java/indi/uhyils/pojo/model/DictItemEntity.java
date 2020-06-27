package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 字典项
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月16日 14时50分
 */
public class DictItemEntity extends BaseVoEntity {
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
    private Integer sortOrder;

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

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
