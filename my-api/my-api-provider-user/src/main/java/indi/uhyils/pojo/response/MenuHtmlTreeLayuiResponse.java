package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.MenuEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * menu菜单页获取数据用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月02日 17时14分
 */
public class MenuHtmlTreeLayuiResponse implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 表字段名(不知道干什么的)
     */
    private String field;

    /**
     * 子节点
     */
    private List<MenuHtmlTreeLayuiResponse> children = new ArrayList<>();

    /**
     * 点击节点弹出新窗口对应的 url。需开启 isJump 参数
     */
    private String href;

    /**
     * 是否展开
     */
    private Boolean spread = false;

    /**
     * 是否选中
     */
    private Boolean checked = false;

    /**
     * 是否禁用
     */
    private Boolean disable = false;

    public static MenuHtmlTreeLayuiResponse build(MenuEntity menuEntity) {
        MenuHtmlTreeLayuiResponse response = new MenuHtmlTreeLayuiResponse();
        response.setId(menuEntity.getId());
        response.setHref(menuEntity.getUrl());
        response.setTitle(menuEntity.getName());
        return response;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<MenuHtmlTreeLayuiResponse> getChildren() {
        return children;
    }

    public void setChildren(List<MenuHtmlTreeLayuiResponse> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
}
