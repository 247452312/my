package indi.uhyils.pojo.model;

/**
 * 角色-权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时30分
 */
public class RoleLinkDept extends MiddleEntity {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限集id
     */
    private String deptId;

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
