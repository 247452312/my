package indi.uhyils.pojo.DTO;


/**
 * 角色-部门关联图(RoleDept)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时33分00秒
 */
public class RoleDeptDTO extends IdDTO {

    private static final long serialVersionUID = -27785942140585755L;


    private Long deptId;

    private Long roleId;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
