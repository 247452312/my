package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.DefaultCQE;
import java.util.List;

/**
 * 权限集 添加菜单
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月07日 11时21分
 */
public class PutMenusToDeptRequest extends DefaultCQE {

    private Long deptId;

    private List<Long> menuIds;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
