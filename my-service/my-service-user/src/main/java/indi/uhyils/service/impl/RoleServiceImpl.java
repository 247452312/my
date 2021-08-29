package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DeptAssembler;
import indi.uhyils.assembler.RoleAssembler;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.request.PutDeptsToRoleCommand;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.DeptId;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.entity.RoleId;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.service.RoleService;
import indi.uhyils.util.AssertUtil;
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
    public RoleDTO getRoleByRoleId(IdQuery request) {
        RoleId roleId = new RoleId(request.getId());
        Role role = roleId.completion(rep);
        AssertUtil.assertTrue(role != null, "查询失败");
        return assem.toDTO(role);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept"})
    public Boolean putDeptsToRole(PutDeptsToRoleCommand request) {
        RoleId roleId = new RoleId(request.getRoleId());

        roleId.cleanDeptLink(rep);
        roleId.forceInitDeptIds(request.getDeptIds().stream().map(DeptId::new).collect(Collectors.toList()));
        roleId.createDeptLink(rep);
        return true;
    }

    @Override
    public Boolean deleteRoleDept(IdsCommand idsRequest) {
        rep.removeRoleDeptLink(idsRequest.getIds());
        return true;
    }

    @Override
    public List<RoleDTO> getRoles(DefaultCQE request) {
        List<Role> roles = rep.getAll();
        return roles.stream().map(assem::toDTO).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dept", "sys_role_dept"})
    public List<DeptDTO> getUserDeptsByRoleId(IdQuery request) {
        RoleId roleId = new RoleId(request.getId());
        roleId.fillDeptIds(rep);
        List<DeptId> deptIds = roleId.deptIds();
        List<Dept> list = deptRepository.find(deptIds);
        return list.stream().map(t -> deptAssembler.toDTO(t)).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_role_dept", "sys_dept"})
    public List<GetAllDeptWithHaveMarkDTO> getAllDeptWithHaveMark(IdQuery request) {
        RoleId roleId = new RoleId(request.getId());
        return roleId.toDeptWithHaveMark(rep);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept", "sys_user"})
    public Boolean deleteRole(IdCommand request) {
        RoleId roleId = new RoleId(request.getId());
        roleId.cleanDeptLink(rep);
        rep.remove(roleId);
        return true;
    }

}