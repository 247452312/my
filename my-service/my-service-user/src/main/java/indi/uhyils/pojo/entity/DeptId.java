package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 14时33分
 */
public class DeptId extends Identifier {

    private final Long deptId;

    private List<PowerId> powerIds;

    private List<MenuId> menuIds;

    public DeptId(Long deptId) {
        super(deptId);
        this.deptId = deptId;
    }

    public DeptId(Long deptId, List<PowerId> powerIds) {
        super(deptId);
        this.deptId = deptId;
        this.powerIds = powerIds;
    }

    public DeptId(List<Long> powerIds, Long deptId) {
        this(deptId, powerIds.stream().map(PowerId::new).collect(Collectors.toList()));
    }

    /**
     * 获取id值
     *
     * @return
     */
    public Long deptIdValue() {
        return deptId;
    }

    public void addPower(List<Long> powerIds, DeptRepository rep) {
        List<PowerId> newPowerIds = addPowerToEntity(powerIds);

        for (PowerId newPowerId : newPowerIds) {
            rep.addPowers(this, newPowerId);
        }

    }

    public List<PowerId> addPowerToEntity(List<Long> powerIds) {
        if (this.powerIds == null) {
            this.powerIds = new ArrayList<>(powerIds.size());
        }
        List<PowerId> newPowerIds = powerIds.stream().map(PowerId::new).collect(Collectors.toList());
        List<PowerId> result = new ArrayList<>();
        for (PowerId newPowerId : newPowerIds) {
            if (this.powerIds.contains(newPowerId)) {
                continue;
            }
            this.powerIds.add(newPowerId);
            result.add(newPowerId);
        }
        return result;
    }

    public void cleanPower(DeptRepository rep) {
        this.powerIds = null;
        rep.cleanPower(this);

    }

    public void cleanMenu(DeptRepository rep) {
        this.menuIds = null;
        rep.cleanMenu(this);
    }

    public void addMenu(List<Long> menuIds, DeptRepository rep) {
        List<MenuId> newPowerIds = addMenuToEntity(menuIds);

        for (MenuId newPowerId : newPowerIds) {
            rep.addMenu(this, newPowerId);
        }
    }

    private List<MenuId> addMenuToEntity(List<Long> menuIds) {
        if (this.menuIds == null) {
            this.menuIds = new ArrayList<>(menuIds.size());
        }
        List<MenuId> newMenuIds = menuIds.stream().map(MenuId::new).collect(Collectors.toList());
        List<MenuId> result = new ArrayList<>();
        for (MenuId newMenuId : newMenuIds) {
            if (this.menuIds.contains(newMenuId)) {
                continue;
            }
            this.menuIds.add(newMenuId);
            result.add(newMenuId);
        }
        return result;
    }

}
