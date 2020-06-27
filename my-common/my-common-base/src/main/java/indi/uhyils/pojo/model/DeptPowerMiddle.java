package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseMiddleEntity;

/**
 * 权限集-权限
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时32分
 */
public class DeptPowerMiddle extends BaseMiddleEntity {

    /**
     * 权限集id
     */
    private String deptId;

    /**
     * 权限id
     */
    private String powerId;

    public static DeptPowerMiddle build(String deptId, String powerId) {
        DeptPowerMiddle deptPowerMiddle = new DeptPowerMiddle();
        deptPowerMiddle.setDeptId(deptId);
        deptPowerMiddle.setPowerId(powerId);
        return deptPowerMiddle;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }

}
