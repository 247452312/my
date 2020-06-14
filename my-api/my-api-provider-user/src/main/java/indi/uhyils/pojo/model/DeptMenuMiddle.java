package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.MiddleEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * dept和菜单的中间表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时39分
 */
public class DeptMenuMiddle extends MiddleEntity {

    private String deptId;
    private String menuId;

    public static DeptMenuMiddle build(String deptId, String menuId) {
        DeptMenuMiddle deptMenuMiddle = new DeptMenuMiddle();
        deptMenuMiddle.setDeptId(deptId);
        deptMenuMiddle.setMenuId(menuId);
        return deptMenuMiddle;
    }

    public static List<DeptMenuMiddle> build(String deptId, List<String> menuIds) {
        return menuIds.stream().map(t -> build(deptId, t)).collect(Collectors.toList());
    }

    public static List<DeptMenuMiddle> build(List<String> deptIds, String menuId) {
        return deptIds.stream().map(t -> build(t, menuId)).collect(Collectors.toList());
    }

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
