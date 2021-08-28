package indi.uhyils.pojo.DTO;


/**
 * 部门-菜单关联(DeptMenu)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分25秒
 */
public class DeptMenuDTO extends IdDTO {

    private static final long serialVersionUID = -65409261254967289L;


    private Long deptId;

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
