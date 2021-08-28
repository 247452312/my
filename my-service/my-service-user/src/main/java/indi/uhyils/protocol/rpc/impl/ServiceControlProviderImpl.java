package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.MethodDisableDTO;
import indi.uhyils.pojo.DTO.request.DelMethodDisableCommand;
import indi.uhyils.pojo.DTO.request.MethodDisableQuery;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.AddCommand;
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
@ReadWriteMark
public class ServiceControlProviderImpl implements ServiceControlProvider {


    @Autowired
    private ServiceControlService service;

    @Override
    public ServiceResult<Boolean> getMethodDisable(MethodDisableQuery request) {
        return service.getMethodDisable(request);
    }

    @Override
    public ServiceResult<List<MethodDisableDTO>> getAllMethodDisable(DefaultCQE request) {
        return service.getAllMethodDisable(request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> addOrEditMethodDisable(AddCommand<MethodDisableDTO> request) {
        return service.addOrEditMethodDisable(request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> delMethodDisable(DelMethodDisableCommand request) {
        return service.delMethodDisable(request);
    }
}
