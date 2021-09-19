package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.RoleAssembler;
import indi.uhyils.dao.RoleDao;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.RoleDeptDO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Role;
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
public class RoleRepositoryImpl extends AbstractRepository<Role, RoleDO, RoleDao, RoleDTO, RoleAssembler> implements RoleRepository {


    protected RoleRepositoryImpl(RoleAssembler assembler, RoleDao dao) {
        super(assembler, dao);
    }

    @Override
    public void cleanDeptLink(Role roleId) {
        dao.deleteRoleDeptMiddleByRoleId(roleId.getUnique().getId());
    }

    @Override
    public void addRoleDeptLink(Role roleId, List<Dept> deptIds) {
        RoleDeptDO roleDeptDO = new RoleDeptDO();
        roleDeptDO.setRoleId(roleId.getUnique().getId());
        for (Dept deptId : deptIds) {
            roleDeptDO.setDeptId(deptId.getUnique().getId());
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
    public List<Role> findRoleDeptLinkByRoleId(Role roleId) {
        List<RoleDeptDO> roleDeptDOS = dao.getRoleDeptLinkByRoleId(roleId.getUnique().getId());
        return roleDeptDOS.stream().map(assembler::RoleDeptToEntity).collect(Collectors.toList());
    }

    @Override
    public List<GetAllDeptWithHaveMarkDTO> findDeptWithHaveMark(Role roleId) {
        return dao.getAllDeptWithHaveMark(roleId.getUnique().getId());
    }


}
