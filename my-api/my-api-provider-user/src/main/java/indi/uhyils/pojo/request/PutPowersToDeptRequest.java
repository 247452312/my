package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

import java.util.List;

/**
 * 将权限授予权限集的请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 07时37分
 */
public class PutPowersToDeptRequest extends DefaultRequest {

    private Long deptId;

    private List<Long> powerIds;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<Long> getPowerIds() {
        return powerIds;
    }

    public void setPowerIds(List<Long> powerIds) {
        this.powerIds = powerIds;
    }
}
