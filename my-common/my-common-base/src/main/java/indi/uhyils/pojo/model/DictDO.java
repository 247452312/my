package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;
import java.util.List;

/**
 * 字典表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月16日 14时45分
 */
public class DictDO extends BaseDoDO {

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

    private List<DictItemDO> items;

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

    public List<DictItemDO> getItems() {
        return items;
    }

    public void setItems(List<DictItemDO> items) {
        this.items = items;
    }
}
