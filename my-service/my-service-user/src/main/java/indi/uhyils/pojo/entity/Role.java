package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时37分
 */
public class Role extends AbstractDoEntity<RoleDO> {

    private List<Dept> depts;

    public Role(RoleDO roleDO) {
        super(roleDO);
    }

    /**
     * 填充部门与权限
     *
     * @param deptRepository
     * @param powerRepository
     */
    public void initDept(DeptRepository deptRepository, PowerRepository powerRepository, MenuRepository menuRepository) {
        if (this.depts != null) {
            return;
        }
        this.depts = deptRepository.findByRoleId(new Identifier(getData().getId()));
        for (Dept dept : depts) {
            dept.initMenus(menuRepository);
            dept.initPower(powerRepository);
        }
    }


    public List<Menu> menus() {
        List<Menu> result = new ArrayList<>();
        for (Dept dept : depts) {
            result.addAll(dept.menus());
        }
        return result;
    }
}
