package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.Public;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.request.PutDeptsToRoleCommand;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.protocol.rpc.RoleProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.RoleService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时27分
 */
@RpcService
public class RoleProviderImpl extends BaseDefaultProvider<RoleDTO> implements RoleProvider {


    @Autowired
    private RoleService service;


    @Override
    @Public
    public RoleDTO getRoleByRoleId(IdQuery request) {
        Identifier roleId = new Identifier(request.getId());
        return service.getRoleByRoleId(roleId);
    }

    @Override
    public Boolean putDeptsToRole(PutDeptsToRoleCommand request) {
        Identifier roleId = new Identifier(request.getRoleId());
        List<Identifier> deptIds = request.getDeptIds().stream().map(Identifier::new).collect(Collectors.toList());
        return service.putDeptsToRole(roleId, deptIds);
    }

    @Override
    public Boolean deleteRoleDept(IdsCommand idsRequest) {
        List<Long> roleDeptId = idsRequest.getIds();
        return service.deleteRoleDept(roleDeptId);
    }

    @Override
    public List<RoleDTO> getRoles(DefaultCQE request) {
        return service.getRoles();
    }

    @Override
    public List<DeptDTO> getUserDeptsByRoleId(IdQuery request) {
        Identifier roleId = new Identifier(request.getId());
        return service.getUserDeptsByRoleId(roleId);
    }

    @Override
    public List<GetAllDeptWithHaveMarkDTO> getAllDeptWithHaveMark(IdQuery request) {
        Identifier roleId = new Identifier(request.getId());
        return service.getAllDeptWithHaveMark(roleId);
    }

    @Override
    public Boolean deleteRole(IdCommand request) {
        Identifier roleId = new Identifier(request.getId());
        return service.deleteRole(roleId);
    }

    @Override
    protected BaseDoService<RoleDTO> getService() {
        return service;
    }
}
