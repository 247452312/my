package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseMiddleDO;

/**
 * 权限集-权限
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时32分
 */
public class DeptPowerMiddle extends BaseMiddleDO {

    /**
     * 权限集id
     */
    private Long deptId;

    /**
     * 权限id
     */
    private Long powerId;

    public static DeptPowerMiddle build(Long deptId, Long powerId) {
        DeptPowerMiddle deptPowerMiddle = new DeptPowerMiddle();
        deptPowerMiddle.setDeptId(deptId);
        deptPowerMiddle.setPowerId(powerId);
        return deptPowerMiddle;
    }

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
