package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.DataEntity;

/**
 * 菜单
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时36分
 */
public class MenuEntity extends DataEntity {

    /**
     * 场景
     */
    private Integer iFrame;

    /**
     * 排序, 序号越小越靠前
     */
    private Integer sort;
    /**
     * 非叶子结点->false 叶子结点->true
     */
    private Boolean type;

    /**
     * 页面的跳转方式
     * _self->iframe修改
     * _blank->整个页面跳转
     * 空->不跳
     */
    private String target;

    /**
     * 父节点id
     */
    private String fid;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单icon
     */
    private String icon;

    /**
     * 菜单所指的url
     */
    private String url;


    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getiFrame() {
        return iFrame;
    }

    public void setiFrame(Integer iFrame) {
        this.iFrame = iFrame;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
