package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 部门-菜单关联(DeptMenu)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 08时53分17秒
 */
@TableName(value = "sys_dept_menu")
public class DeptMenuDO extends BaseDO {

    private static final long serialVersionUID = 583887797316289311L;


    @TableField
    private Long deptId;

    @TableField
    private Long menuId;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}
