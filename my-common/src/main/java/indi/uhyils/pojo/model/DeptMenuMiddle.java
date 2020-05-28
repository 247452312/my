package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.MiddleEntity;

/**
 * dept和菜单的中间表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时39分
 */
public class DeptMenuMiddle extends MiddleEntity {

    private String deptId;
    private String menuId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
