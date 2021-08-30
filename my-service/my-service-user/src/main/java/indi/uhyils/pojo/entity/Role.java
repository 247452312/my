package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.RoleDeptDO;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.RoleRepository;
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

    public Role(Long id) {
        super(id, new RoleDO());
    }

    public Role(RoleDeptDO roleDO) {
        super(parseRoleDeptDo(roleDO));
        this.depts = new ArrayList<>(1);
        DeptDO deptDO = new DeptDO();
        deptDO.setId(roleDO.getDeptId());
        depts.add(new Dept(deptDO));
    }

    private static RoleDO parseRoleDeptDo(RoleDeptDO roleDO) {
        RoleDO roleDo = new RoleDO();
        roleDo.setId(roleDO.getRoleId());
        return roleDo;
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
        this.depts = deptRepository.findByRoleId(id);
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


    public void forceInitDeptIds(List<Dept> depts) {
        this.depts = depts;
    }

    public void cleanDeptLink(RoleRepository rep) {
        this.depts = null;
        rep.cleanDeptLink(this);
    }

    public List<Dept> deptIds() {
        return depts;
    }

    public void createDeptLink(RoleRepository rep) {
        rep.addRoleDeptLink(this, depts);
    }

    /**
     * 填充deptId
     *
     * @param rep
     */
    public void fillDeptIds(RoleRepository rep) {
        List<Role> roles = rep.findRoleDeptLinkByRoleId(this);
        this.depts = new ArrayList<>(roles.size());
        roles.stream().map(t -> t.depts).forEach(t -> this.depts.addAll(t));
    }

    public List<GetAllDeptWithHaveMarkDTO> toDeptWithHaveMark(RoleRepository rep) {
        return rep.findDeptWithHaveMark(this);
    }

    public void removeSelf(RoleRepository rep) {
        rep.remove(getId());
    }
}
