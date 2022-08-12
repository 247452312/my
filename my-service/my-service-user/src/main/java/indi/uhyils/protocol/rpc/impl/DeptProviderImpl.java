package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DeptDTO;
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
    public Boolean putPowersToDept(PutPowersToDeptCommand request) throws Exception {
        Identifier deptId = new Identifier(request.getDeptId());
        List<Identifier> powerIds = request.getPowerIds().stream().map(Identifier::new).collect(Collectors.toList());
        return service.putPowersToDept(deptId, powerIds);
    }

    @Override
    public Boolean deleteDeptPower(IdsCommand request) {
        List<Long> ids = request.getIds();
        return service.deleteDeptPower(ids);
    }

    @Override
    public Boolean putMenusToDept(PutMenusToDeptsCommand request) {
        Identifier deptId = new Identifier(request.getDeptId());
        List<Identifier> menusId = request.getMenuIds().stream().map(Identifier::new).collect(Collectors.toList());
        return service.putMenusToDept(deptId, menusId);
    }

    @Override
    public List<DeptDTO> getDepts(DefaultCQE request) {
        return service.getDepts();
    }

    @Override
    public List<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(IdQuery request) {
        Identifier deptId = new Identifier(request.getId());
        return service.getAllPowerWithHaveMark(deptId);

    }

    @Override
    public Boolean deleteDept(IdCommand request) {
        Identifier deptId = new Identifier(request.getId());
        return service.deleteDept(deptId);

    }

    @Override
    protected BaseDoService<DeptDTO> getService() {
        return service;
    }

}



