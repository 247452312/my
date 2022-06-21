package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DeptAssembler;
import indi.uhyils.assembler.RoleAssembler;
import indi.uhyils.enums.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.service.RoleService;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色(Role)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分59秒
 */
@Service
@ReadWriteMark(tables = {"sys_role"})
public class RoleServiceImpl extends AbstractDoService<RoleDO, Role, RoleDTO, RoleRepository, RoleAssembler> implements RoleService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptAssembler deptAssembler;

    public RoleServiceImpl(RoleAssembler assembler, RoleRepository repository) {
        super(assembler, repository);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept", "sys_role_dept"})
    public RoleDTO getRoleByRoleId(Identifier roleId) {
        Role role = new Role(roleId);
        role.completion(rep);
        Asserts.assertTrue(role.toData() != null, "查询失败");
        return assem.toDTO(role);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept"})
    public Boolean putDeptsToRole(Identifier roleId, List<Identifier> deptIds) {
        Role role = new Role(roleId);

        role.cleanDeptLink(rep);
        role.forceInitDeptIds(deptIds.stream().map(Dept::new).collect(Collectors.toList()));
        role.mappingToDB(rep);
        return true;
    }

    @Override
    public Boolean deleteRoleDept(List<Long> roleDeptId) {
        rep.removeRoleDeptLink(roleDeptId);
        return true;
    }

    @Override
    public List<RoleDTO> getRoles() {
        List<Role> roles = rep.getAll();
        return roles.stream().map(assem::toDTO).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dept", "sys_role_dept"})
    public List<DeptDTO> getUserDeptsByRoleId(Identifier roleId) {
        Role role = new Role(roleId);
        role.fillDeptIds(rep);
        List<Dept> deptIds = role.deptIds();
        List<Dept> list = deptRepository.find(deptIds.stream().map(BaseEntity::getUnique).collect(Collectors.toList()));
        return list.stream().map(t -> deptAssembler.toDTO(t)).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_role_dept", "sys_dept"})
    public List<GetAllDeptWithHaveMarkDTO> getAllDeptWithHaveMark(Identifier roleId) {
        Role role = new Role(roleId);
        return role.toDeptWithHaveMark(rep);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept", "sys_user"})
    public Boolean deleteRole(Identifier roleId) {
        Role role = new Role(roleId);
        role.cleanDeptLink(rep);
        role.removeSelf(rep);
        return true;
    }

}
