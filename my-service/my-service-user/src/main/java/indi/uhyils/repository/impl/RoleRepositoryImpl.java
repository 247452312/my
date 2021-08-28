package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.RoleAssembler;
import indi.uhyils.dao.RoleDao;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.RoleDeptDO;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.entity.DeptId;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.entity.RoleDept;
import indi.uhyils.pojo.entity.RoleId;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role, RoleDO, RoleDao, RoleAssembler> implements RoleRepository {


    protected RoleRepositoryImpl(RoleAssembler assembler, RoleDao dao) {
        super(assembler, dao);
    }

    @Override
    public void cleanDeptLink(RoleId roleId) {
        dao.deleteRoleDeptMiddleByRoleId(roleId.roleIdValue());
    }

    @Override
    public void addRoleDeptLink(RoleId roleId, List<DeptId> deptIds) {
        RoleDeptDO roleDeptDO = new RoleDeptDO();
        roleDeptDO.setRoleId(roleId.roleIdValue());
        for (DeptId deptId : deptIds) {
            roleDeptDO.setDeptId(deptId.deptIdValue());
            roleDeptDO.preInsert();
            dao.insertRoleDept(roleDeptDO);
        }
    }

    @Override
    public void removeRoleDeptLink(List<Long> ids) {
        dao.deleteRoleDept(ids);
    }

    @Override
    public List<Role> getAll() {
        List<RoleDO> all = dao.getAll();
        return all.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<RoleDept> findRoleDeptLinkByRoleId(RoleId roleId) {
        List<RoleDeptDO> roleDeptDOS = dao.getRoleDeptLinkByRoleId(roleId.roleIdValue());
        return roleDeptDOS.stream().map(assembler::RoleDeptToEntity).collect(Collectors.toList());
    }

    @Override
    public List<GetAllDeptWithHaveMarkDTO> findDeptWithHaveMark(RoleId roleId) {
        return dao.getAllDeptWithHaveMark(roleId.roleIdValue());
    }


}
