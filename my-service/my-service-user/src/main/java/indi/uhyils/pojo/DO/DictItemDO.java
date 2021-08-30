package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 数据字典子项(DictItem)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分41秒
 */
public class DictItemDO extends BaseDO {

    private static final long serialVersionUID = -37338752281642543L;


    private String description;

    private Long dictId;

    private Integer sortOrder;

    private String text;

    private String value;

    private Integer status;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }


    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
