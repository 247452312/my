package indi.uhyils.pojo.response;

import java.io.Serializable;

/**
 * 获取所有包含羁绊标记的叶子菜单
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月07日 10时59分
 */
public class GetAllMenuWithHaveMarkResponse implements Serializable {

    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 这个权限集是否包含这个叶子菜单
     */
    private Boolean mark;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }
}
