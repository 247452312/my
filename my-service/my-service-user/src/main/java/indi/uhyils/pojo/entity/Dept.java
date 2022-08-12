package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
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

    @Default
    public Dept(DeptDO data) {
        super(data);
    }

    public Dept(Long id) {
        super(parseIdToDO(id));
    }

    private static DeptDO parseIdToDO(Long id) {
        DeptDO deptDO = new DeptDO();
        deptDO.setId(id);
        return deptDO;
    }

    public Dept(Identifier id) {
        super(id, new DeptDO());
    }

    public Dept(DeptDO deptDO, List<Power> powers) {
        super(deptDO);
        this.powers = powers;
    }

    public Dept(List<Menu> menus, DeptDO deptDO) {
        super(deptDO);
        this.menus = menus;
    }

    public void addPower(List<Power> powers, DeptRepository rep) {
        List<Power> newPowerIds = addPowerToEntity(powers);
        for (Power newPowerId : newPowerIds) {
            rep.addPowers(this, newPowerId);
        }
    }

    public List<Power> addPowerToEntity(List<Power> powerIds) {
        if (this.powers == null) {
            this.powers = new ArrayList<>(powerIds.size());
        }
        List<Power> result = new ArrayList<>();
        for (Power newPowerId : powerIds) {
            if (this.powers.contains(newPowerId)) {
                continue;
            }
            this.powers.add(newPowerId);
            result.add(newPowerId);
        }
        return result;
    }


    public void cleanPower(DeptRepository rep) {
        this.powers = null;
        rep.cleanPower(this);

    }

    public void cleanMenu(DeptRepository rep) {
        this.menus = null;
        rep.cleanMenu(this);
    }

    public void addMenu(List<Menu> menus, DeptRepository rep) {
        List<Menu> newPowerIds = addMenuToEntity(menus);

        for (Menu newPowerId : newPowerIds) {
            rep.addMenu(this, newPowerId);
        }
    }

    private List<Menu> addMenuToEntity(List<Menu> menuIds) {
        if (this.menus == null) {
            this.menus = new ArrayList<>(menuIds.size());
        }
        List<Menu> result = new ArrayList<>();
        for (Menu newMenuId : menuIds) {
            if (this.menus.contains(newMenuId)) {
                continue;
            }
            this.menus.add(newMenuId);
            result.add(newMenuId);
        }
        return result;
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
        this.powers = powerRepository.findByDeptId(new Identifier(data.getId()));
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
        this.menus = menuRepository.findByDeptId(new Identifier(data.getId()));
    }

    public List<Menu> menus() {
        return menus;
    }

    public void removeMenuLink(DeptRepository repository) {
        if (CollectionUtil.isEmpty(menus)) {
            return;
        }
        repository.cleanMenu(this);
        this.menus = null;
    }

    public void removePowerLink(DeptRepository repository) {
        if (CollectionUtil.isEmpty(powers)) {
            return;
        }
        repository.cleanPower(this);
        this.powers = null;
    }

    public void removeRoleLink(DeptRepository repository) {
        repository.cleanRole(this);
    }

    public List<Power> powers() {
        return powers;
    }
}
