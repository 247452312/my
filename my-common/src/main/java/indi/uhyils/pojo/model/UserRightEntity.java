package indi.uhyils.pojo.model;


import java.util.List;

/**
 * 用户权限
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月28日 17时13分
 */
public class UserRightEntity extends DataEntity {

    /**
     * 父节点串
     */
    private String parentIds;
    /**
     * 父节点
     */
    private String parentId;
    /**
     * 权限名称
     */
    private String name;
    /**
     * url
     */
    private String url;

    /**
     * 子节点们
     */
    private List<UserRightEntity> chds;

    public List<UserRightEntity> getChds() {
        return chds;
    }

    public void setChds(List<UserRightEntity> chds) {
        this.chds = chds;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
