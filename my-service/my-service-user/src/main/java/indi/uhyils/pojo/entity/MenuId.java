package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 15时13分
 */
public class MenuId extends Identifier {

    private final Long menuId;

    private List<DeptId> deptIds;

    public MenuId(Long menuId) {
        super(menuId);
        this.menuId = menuId;
    }

    public Long menuIdValue() {
        return menuId;
    }

    public void cleanDept(MenuRepository rep) {
        this.deptIds = null;
        rep.cleanDept(this);
    }

    public void addDepts(List<Long> deptIds, MenuRepository rep) {
        List<DeptId> newDeptIds = addDeptsToEntity(deptIds);

        for (DeptId newDeptId : newDeptIds) {
            rep.addDept(this, newDeptId);
        }
    }

    public List<DeptId> addDeptsToEntity(List<Long> deptIds) {
        if (this.deptIds == null) {
            this.deptIds = new ArrayList<>(deptIds.size());
        }
        List<DeptId> newDeptIds = deptIds.stream().map(DeptId::new).collect(Collectors.toList());
        List<DeptId> result = new ArrayList<>();
        for (DeptId newDeptId : newDeptIds) {
            if (this.deptIds.contains(newDeptId)) {
                continue;
            }
            this.deptIds.add(newDeptId);
            result.add(newDeptId);
        }
        return result;
    }

    public Menu toMenu(MenuRepository rep) {
        return rep.find(this);
    }
}
