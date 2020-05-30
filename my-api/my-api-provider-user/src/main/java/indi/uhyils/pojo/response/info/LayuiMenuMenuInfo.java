package indi.uhyils.pojo.response.info;

import indi.uhyils.pojo.model.MenuEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class LayuiMenuMenuInfo implements Serializable {
    private String id;
    private String fid;
    private String title;
    private String icon;
    private String href;
    private String target;
    private List<LayuiMenuMenuInfo> child = new ArrayList<>();

    public static LayuiMenuMenuInfo build(MenuEntity menuEntity) {
        LayuiMenuMenuInfo layuiMenuMenuInfo = new LayuiMenuMenuInfo();
        layuiMenuMenuInfo.setFid(menuEntity.getFid());
        layuiMenuMenuInfo.setId(menuEntity.getId());
        layuiMenuMenuInfo.setTarget(menuEntity.getTarget());
        layuiMenuMenuInfo.setTitle(menuEntity.getName());
        layuiMenuMenuInfo.setIcon(menuEntity.getIcon());
        layuiMenuMenuInfo.setHref(menuEntity.getUrl());
        return layuiMenuMenuInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
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

    public List<LayuiMenuMenuInfo> getChild() {
        return child;
    }

    public void setChild(List<LayuiMenuMenuInfo> child) {
        this.child = child;
    }
}
