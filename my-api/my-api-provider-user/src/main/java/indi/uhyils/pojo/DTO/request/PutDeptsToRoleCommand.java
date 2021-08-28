package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.DefaultCQE;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 07时38分
 */
public class PutDeptsToRoleCommand extends DefaultCQE {

    private Long roleId;

    private List<Long> deptIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }
}
