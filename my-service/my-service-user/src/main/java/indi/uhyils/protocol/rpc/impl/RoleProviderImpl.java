package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.request.PutDeptsToRoleCommand;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.RoleProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时27分
 */
@RpcService
@ReadWriteMark(tables = {"sys_role"})
public class RoleProviderImpl extends BaseDefaultProvider<RoleDTO> implements RoleProvider {


    @Autowired
    private RoleService service;


    @Override
    @NoToken
    public ServiceResult<RoleDTO> getRoleByRoleId(IdQuery request) {
        return service.getRoleByRoleId(request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept", "sys_role_dept"})
    public ServiceResult<Boolean> putDeptsToRole(PutDeptsToRoleCommand request) throws Exception {
        return service.putDeptsToRole(request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept"})
    public ServiceResult<Boolean> deleteRoleDept(IdsCommand idsRequest) {
        return service.deleteRoleDept(idsRequest);
    }

    @Override
    public ServiceResult<List<RoleDTO>> getRoles(DefaultCQE request) {
        return service.getRoles(request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dept", "sys_role_dept"})
    public ServiceResult<List<DeptDTO>> getUserDeptsByRoleId(IdQuery request) {
        return service.getUserDeptsByRoleId(request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_role_dept", "sys_dept"})
    public ServiceResult<List<GetAllDeptWithHaveMarkDTO>> getAllDeptWithHaveMark(IdQuery request) {
        return service.getAllDeptWithHaveMark(request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_role_dept", "sys_user"})
    public ServiceResult<Boolean> deleteRole(IdCommand request) {
        return service.deleteRole(request);
    }

    @Override
    protected BaseDoService<RoleDTO> getService() {
        return service;
    }
}
