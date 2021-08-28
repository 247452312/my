package indi.uhyils.service.impl;

import indi.uhyils.assembler.DeptAssembler;
import indi.uhyils.assembler.RoleAssembler;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.request.PutDeptsToRoleCommand;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
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
public class RoleServiceImpl extends AbstractDoService<RoleDO, Role, RoleDTO, RoleRepository, RoleAssembler> implements RoleService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptAssembler deptAssembler;

    public RoleServiceImpl(RoleAssembler assembler, RoleRepository repository) {
        super(assembler, repository);
    }

    @Override
    public ServiceResult<RoleDTO> getRoleByRoleId(IdQuery request) {
        RoleId roleId = new RoleId(request.getId());
        Role role = roleId.completion(rep);
        if (role == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        return ServiceResult.buildSuccessResult("获取用户角色成功", assem.toDTO(role));
    }

    @Override
    public ServiceResult<Boolean> putDeptsToRole(PutDeptsToRoleCommand request) throws Exception {
        RoleId roleId = new RoleId(request.getRoleId());

        roleId.cleanDeptLink(rep);
        roleId.forceInitDeptIds(request.getDeptIds().stream().map(DeptId::new).collect(Collectors.toList()));
        roleId.createDeptLink(rep);
        return ServiceResult.buildSuccessResult("角色添加权限集成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<Boolean> deleteRoleDept(IdsCommand idsRequest) {
        rep.removeRoleDeptLink(idsRequest.getIds());
        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<List<RoleDTO>> getRoles(DefaultCQE request) {
        List<Role> roles = rep.getAll();
        return ServiceResult.buildSuccessResult("查询成功", roles.stream().map(assem::toDTO).collect(Collectors.toList()));
    }

    @Override
    public ServiceResult<List<DeptDTO>> getUserDeptsByRoleId(IdQuery request) {
        RoleId roleId = new RoleId(request.getId());
        roleId.fillDeptIds(rep);
        List<DeptId> deptIds = roleId.deptIds();
        List<Dept> list = deptRepository.find(deptIds);
        List<DeptDTO> deptDTOS = list.stream().map(t -> deptAssembler.toDTO(t)).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询角色成功", deptDTOS);
    }

    @Override
    public ServiceResult<List<GetAllDeptWithHaveMarkDTO>> getAllDeptWithHaveMark(IdQuery request) {
        RoleId roleId = new RoleId(request.getId());
        List<GetAllDeptWithHaveMarkDTO> allDeptWithHaveMark = roleId.toDeptWithHaveMark(rep);
        return ServiceResult.buildSuccessResult("查询带存在标记的权限集成功", allDeptWithHaveMark);
    }

    @Override
    public ServiceResult<Boolean> deleteRole(IdCommand request) {
        RoleId roleId = new RoleId(request.getId());
        roleId.cleanDeptLink(rep);
        rep.remove(roleId);
        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }

}
