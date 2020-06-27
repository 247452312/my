package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

import java.util.List;

/**
 * 将许多菜单添加到一个权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月06日 13时53分
 */
public class PutMenusToDeptsRequest extends DefaultRequest {

    private String deptId;

    private List<String> menuIds;


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }
}
