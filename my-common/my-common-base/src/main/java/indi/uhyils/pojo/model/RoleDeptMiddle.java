package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseMiddleDO;

/**
 * 角色-权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时30分
 */
public class RoleDeptMiddle extends BaseMiddleDO {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限集id
     */
    private Long deptId;

    public static RoleDeptMiddle build(Long roleId, Long deptId) {
        RoleDeptMiddle roleDeptMiddle = new RoleDeptMiddle();
        roleDeptMiddle.setRoleId(roleId);
        roleDeptMiddle.setDeptId(deptId);
        return roleDeptMiddle;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
