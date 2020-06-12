package indi.uhyils.pojo.request;

import java.util.List;

/**
 * 菜单赋权
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月06日 14时20分
 */
public class PutDeptsToMenuRequest extends DefaultRequest {


    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 权限集id们
     */
    private List<String> deptIds;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<String> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }
}
