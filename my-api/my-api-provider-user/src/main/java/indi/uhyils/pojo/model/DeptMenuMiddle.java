package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseMiddleEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * dept和菜单的中间表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时39分
 */
public class DeptMenuMiddle extends BaseMiddleEntity {

    private Long deptId;
    private Long menuId;

    public static DeptMenuMiddle build(Long deptId, Long menuId) {
        DeptMenuMiddle deptMenuMiddle = new DeptMenuMiddle();
        deptMenuMiddle.setDeptId(deptId);
        deptMenuMiddle.setMenuId(menuId);
        return deptMenuMiddle;
    }

    public static List<DeptMenuMiddle> build(Long deptId, List<Long> menuIds) {
        return menuIds.stream().map(t -> build(deptId, t)).collect(Collectors.toList());
    }

    public static List<DeptMenuMiddle> build(List<Long> deptIds, Long menuId) {
        return deptIds.stream().map(t -> build(t, menuId)).collect(Collectors.toList());
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
