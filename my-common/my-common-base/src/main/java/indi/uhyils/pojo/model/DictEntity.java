package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoEntity;
import java.util.List;

/**
 * 字典表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月16日 14时45分
 */
public class DictEntity extends BaseDoEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典描述
     */
    private String description;

    /**
     * 值类型0->int 1->String
     */
    private Integer type;

    private List<DictItemEntity> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<DictItemEntity> getItems() {
        return items;
    }

    public void setItems(List<DictItemEntity> items) {
        this.items = items;
    }
}
