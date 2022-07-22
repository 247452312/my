package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.enums.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.MethodDisableDTO;
import indi.uhyils.pojo.DTO.request.DelMethodDisableCommand;
import indi.uhyils.pojo.DTO.request.MethodDisableQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
import indi.uhyils.protocol.rpc.ServiceControlProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ServiceControlService;
import indi.uhyils.util.Asserts;
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
    public Boolean getMethodDisable(MethodDisableQuery request) {
        InterfaceName className = new InterfaceName(request.getClassName());
        MethodName methodName = new MethodName(request.getMethodName());
        ReadWriteTypeEnum methodType = ReadWriteTypeEnum.parse(request.getMethodType()).orElseThrow(() -> Asserts.makeException("方法禁用时,未查询到方法类型"));
        return service.getMethodDisable(className, methodName, methodType);
    }

    @Override
    public List<MethodDisableDTO> getAllMethodDisable(DefaultCQE request) {
        return service.getAllMethodDisable();
    }

    @Override
    public Boolean addOrEditMethodDisable(AddCommand<MethodDisableDTO> request) {
        MethodDisableDTO dto = request.getDto();
        return service.addOrEditMethodDisable(dto);
    }

    @Override
    public Boolean delMethodDisable(DelMethodDisableCommand request) {
        InterfaceName interfaceName = new InterfaceName(request.getClassName());
        MethodName methodName = new MethodName(request.getMethodName());
        return service.delMethodDisable(interfaceName, methodName);
    }
}
