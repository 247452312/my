package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.RoleDeptDO;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月28日 13时58分
 */
public class RoleId extends Identifier {

    private List<DeptId> deptIds;

    public RoleId(Long roleId) {
        super(roleId);
    }

    public void forceInitDeptIds(List<DeptId> deptIds) {
        this.deptIds = deptIds;
    }

    public Role completion(RoleRepository rep) {
        return rep.find(this);
    }

    public void cleanDeptLink(RoleRepository rep) {
        this.deptIds = null;
        rep.cleanDeptLink(this);
    }

    public Long roleIdValue() {
        return getId();
    }

    public List<DeptId> deptIds() {
        return deptIds;
    }

    public void createDeptLink(RoleRepository rep) {
        rep.addRoleDeptLink(this, deptIds);
    }

    /**
     * 填充deptId
     *
     * @param rep
     */
    public void fillDeptIds(RoleRepository rep) {
        List<RoleDept> link = rep.findRoleDeptLinkByRoleId(this);
        deptIds = link.stream().map(AbstractDoEntity::getData).map(RoleDeptDO::getDeptId).map(DeptId::new).collect(Collectors.toList());
    }

    public List<GetAllDeptWithHaveMarkDTO> toDeptWithHaveMark(RoleRepository rep) {
        return rep.findDeptWithHaveMark(this);
    }
}
