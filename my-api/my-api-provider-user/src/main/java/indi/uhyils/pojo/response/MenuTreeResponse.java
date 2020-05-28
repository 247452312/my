package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.MenuEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树状菜单
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 13时47分
 */
public class MenuTreeResponse implements Serializable {

    private String id;
    /**
     * 父节点
     */
    private MenuTreeResponse fatherNode;

    private String icon;

    private String name;

    private Integer sort;

    private Boolean type;

    private String url;

    private List<MenuTreeResponse> subNode = new ArrayList<>();

    public static MenuTreeResponse build(MenuTreeResponse fatherNode, MenuEntity menuEntity) {
        MenuTreeResponse menuTreeResponse = new MenuTreeResponse();
        menuTreeResponse.setFatherNode(fatherNode);
        menuTreeResponse.setId(menuEntity.getId());
        menuTreeResponse.setIcon(menuEntity.getIcon());
        menuTreeResponse.setName(menuEntity.getName());
        menuTreeResponse.setSort(menuEntity.getSort());
        menuTreeResponse.setType(menuEntity.getType());
        menuTreeResponse.setUrl(menuEntity.getUrl());
        return menuTreeResponse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuTreeResponse getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(MenuTreeResponse fatherNode) {
        this.fatherNode = fatherNode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuTreeResponse> getSubNode() {
        return subNode;
    }

    public void setSubNode(List<MenuTreeResponse> subNode) {
        this.subNode = subNode;
    }
}
