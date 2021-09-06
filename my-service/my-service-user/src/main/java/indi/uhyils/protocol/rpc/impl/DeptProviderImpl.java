package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.PutMenusToDeptsCommand;
import indi.uhyils.pojo.DTO.request.PutPowersToDeptCommand;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.protocol.rpc.DeptProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DeptService;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class DeptProviderImpl extends BaseDefaultProvider<DeptDTO> implements DeptProvider {

    @Resource
    private DeptService service;

    @Override
    protected BaseDoService<DeptDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<Boolean> putPowersToDept(PutPowersToDeptCommand request) throws Exception {
        Identifier deptId = new Identifier(request.getDeptId());
        List<Identifier> powerIds = request.getPowerIds().stream().map(Identifier::new).collect(Collectors.toList());
        Boolean result = service.putPowersToDept(deptId, powerIds);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> deleteDeptPower(IdsCommand request) {
        List<Long> ids = request.getIds();
        Boolean result = service.deleteDeptPower(ids);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> putMenusToDept(PutMenusToDeptsCommand request) {
        Boolean result = service.putMenusToDept(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<DeptDTO>> getDepts(DefaultCQE request) {
        List<DeptDTO> result = service.getDepts();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<GetAllPowerWithHaveMarkDTO>> getAllPowerWithHaveMark(IdQuery request) {
        Identifier deptId = new Identifier(request.getId());
        List<GetAllPowerWithHaveMarkDTO> result = service.getAllPowerWithHaveMark(deptId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> deleteDept(IdCommand request) {
        Identifier deptId = new Identifier(request.getId());
        Boolean result = service.deleteDept(deptId);
        return ServiceResult.buildSuccessResult(result);

    }

}



