package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.PutDeptsToRoleCommand;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
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
public class RoleProviderImpl extends BaseDefaultProvider<RoleDTO> implements RoleProvider {


    @Autowired
    private RoleService service;


    @Override
    @NoToken
    public ServiceResult<RoleDTO> getRoleByRoleId(IdQuery request) {
        RoleDTO result = service.getRoleByRoleId(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> putDeptsToRole(PutDeptsToRoleCommand request) throws Exception {
        Boolean result = service.putDeptsToRole(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> deleteRoleDept(IdsCommand idsRequest) {
        Boolean result = service.deleteRoleDept(idsRequest);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<RoleDTO>> getRoles(DefaultCQE request) {
        List<RoleDTO> result = service.getRoles(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<DeptDTO>> getUserDeptsByRoleId(IdQuery request) {
        List<DeptDTO> result = service.getUserDeptsByRoleId(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<GetAllDeptWithHaveMarkDTO>> getAllDeptWithHaveMark(IdQuery request) {
        List<GetAllDeptWithHaveMarkDTO> result = service.getAllDeptWithHaveMark(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> deleteRole(IdCommand request) {
        Boolean result = service.deleteRole(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    protected BaseDoService<RoleDTO> getService() {
        return service;
    }
}
