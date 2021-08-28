package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.util.CollectionUtil;
import java.util.List;


/**
 * 部门
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时14分
 */
public class Dept extends AbstractDoEntity<DeptDO> {

    private List<Power> powers;

    private List<Menu> menus;

    public Dept(DeptDO deptDO) {
        super(deptDO);
    }

    /**
     * 填充权限
     *
     * @param powerRepository
     */
    public void initPower(PowerRepository powerRepository) {
        if (powers != null) {
            return;
        }
        this.powers = powerRepository.findByDeptId(new Identifier(getData().getId()));
    }

    /**
     * 填充权限
     *
     * @param menuRepository
     */
    public void initMenus(MenuRepository menuRepository) {
        if (menus != null) {
            return;
        }
        this.menus = menuRepository.findByDeptId(new Identifier(getData().getId()));
    }

    public List<Menu> menus() {
        return menus;
    }

    public void removeMenuLink(DeptRepository repository) {
        if (CollectionUtil.isEmpty(menus)) {
            return;
        }
    }

    public void removePowerLink(DeptRepository repository) {
        if (CollectionUtil.isEmpty(powers)) {
            return;
        }
    }

    public void removeRoleLink(DeptRepository repository) {
    }
}
