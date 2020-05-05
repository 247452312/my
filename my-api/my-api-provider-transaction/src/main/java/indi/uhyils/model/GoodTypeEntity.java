package indi.uhyils.model;

/**
 * 货物
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时23分
 */
public class GoodTypeEntity extends DataEntity {

    /**
     * 父类型id
     */
    private String parentId;

    /**
     * 父类型
     */
    private GoodTypeEntity parent;

    /**
     * 类型名称
     */
    private String name;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public GoodTypeEntity getParent() {
        return parent;
    }

    public void setParent(GoodTypeEntity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
