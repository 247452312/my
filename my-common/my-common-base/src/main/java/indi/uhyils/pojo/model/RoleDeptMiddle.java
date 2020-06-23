package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseMiddleEntity;

/**
 * 角色-权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时30分
 */
public class RoleDeptMiddle extends BaseMiddleEntity {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限集id
     */
    private String deptId;

    public static RoleDeptMiddle build(String roleId, String deptId) {
        RoleDeptMiddle roleDeptMiddle = new RoleDeptMiddle();
        roleDeptMiddle.setRoleId(roleId);
        roleDeptMiddle.setDeptId(deptId);
        return roleDeptMiddle;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
