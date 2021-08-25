package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import java.util.List;

/**
 * 将许多菜单添加到一个权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月06日 13时53分
 */
public class PutMenusToDeptsRequest extends DefaultRequest {

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
