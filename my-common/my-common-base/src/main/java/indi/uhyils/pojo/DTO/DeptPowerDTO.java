package indi.uhyils.pojo.DTO;


/**
 * 部门-权限关联(DeptPower)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分30秒
 */
public class DeptPowerDTO extends IdDTO {

    private static final long serialVersionUID = 319827773703188360L;


    private Long deptId;

    private Long powerId;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

}
