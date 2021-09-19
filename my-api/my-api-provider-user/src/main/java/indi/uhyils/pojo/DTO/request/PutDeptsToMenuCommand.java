package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import java.util.List;

/**
 * 菜单赋权
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月06日 14时20分
 */
public class PutDeptsToMenuCommand extends AbstractCommand {


    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 权限集id们
     */
    private List<Long> deptIds;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }
}
