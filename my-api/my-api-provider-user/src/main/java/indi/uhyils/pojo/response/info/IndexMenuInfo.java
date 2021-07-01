package indi.uhyils.pojo.response.info;

import indi.uhyils.pojo.model.MenuEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class IndexMenuInfo implements Serializable {
    private Long id;
    private Long fid;
    private String title;
    private String icon;
    private String href;
    private String target;
    private Integer sort;
    private List<IndexMenuInfo> child = new ArrayList<>();

    public static IndexMenuInfo build(MenuEntity menuEntity) {
        IndexMenuInfo menuMenuInfo = new IndexMenuInfo();
        menuMenuInfo.setFid(menuEntity.getFid());
        menuMenuInfo.setId(menuEntity.getId());
        menuMenuInfo.setTarget(menuEntity.getTarget());
        menuMenuInfo.setTitle(menuEntity.getName());
        menuMenuInfo.setIcon(menuEntity.getIcon());
        menuMenuInfo.setHref(menuEntity.getUrl());
        menuMenuInfo.setSort(menuEntity.getSort());
        return menuMenuInfo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<IndexMenuInfo> getChild() {
        return child;
    }

    public void setChild(List<IndexMenuInfo> child) {
        this.child = child;
    }
}
