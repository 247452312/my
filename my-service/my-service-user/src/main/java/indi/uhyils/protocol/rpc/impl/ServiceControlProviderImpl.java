package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.enum_.MethodTypeEnum;
import indi.uhyils.pojo.DTO.MethodDisableDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.DelMethodDisableCommand;
import indi.uhyils.pojo.DTO.request.MethodDisableQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
import indi.uhyils.protocol.rpc.ServiceControlProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ServiceControlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口禁用服务
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class ServiceControlProviderImpl implements ServiceControlProvider {


    @Autowired
    private ServiceControlService service;

    @Override
    public ServiceResult<Boolean> getMethodDisable(MethodDisableQuery request) {
        InterfaceName className = new InterfaceName(request.getClassName());
        MethodName methodName = new MethodName(request.getMethodName());
        MethodTypeEnum methodType = MethodTypeEnum.parse(request.getMethodType());
        Boolean result = service.getMethodDisable(className, methodName, methodType);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<MethodDisableDTO>> getAllMethodDisable(DefaultCQE request) {
        List<MethodDisableDTO> result = service.getAllMethodDisable();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> addOrEditMethodDisable(AddCommand<MethodDisableDTO> request) {
        MethodDisableDTO dto = request.getDto();
        Boolean result = service.addOrEditMethodDisable(dto);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> delMethodDisable(DelMethodDisableCommand request) {
        InterfaceName interfaceName = new InterfaceName(request.getClassName());
        MethodName methodName = new MethodName(request.getMethodName());
        Boolean result = service.delMethodDisable(interfaceName, methodName);
        return ServiceResult.buildSuccessResult(result);
    }
}
