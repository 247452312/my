package indi.uhyils.pojo.request;

import java.util.List;

/**
 * 将权限授予权限集的请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 07时37分
 */
public class PutPowersToDeptRequest extends DefaultRequest {

    private String deptId;

    private List<String> powerIds;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<String> getPowerIds() {
        return powerIds;
    }

    public void setPowerIds(List<String> powerIds) {
        this.powerIds = powerIds;
    }
}
