package indi.uhyils.service.impl;


import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.MethodDisableDTO;
import indi.uhyils.pojo.DTO.request.DelMethodDisableCommand;
import indi.uhyils.pojo.DTO.request.MethodDisableQuery;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.entity.MethodDisable;
import indi.uhyils.repository.ServiceControlRepository;
import indi.uhyils.service.ServiceControlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分43秒
 */
@Service
public class ServiceControlServiceImpl implements ServiceControlService {

    @Autowired
    private ServiceControlRepository repository;

    @Override
    public ServiceResult<Boolean> getMethodDisable(MethodDisableQuery request) {
        MethodDisable methodDisable = new MethodDisable(request);
        methodDisable.completionClassName();
        Boolean enable = methodDisable.checkInterfaceDisable(repository);
        return ServiceResult.buildSuccessResult(enable);
    }

    @Override
    public ServiceResult<List<MethodDisableDTO>> getAllMethodDisable(DefaultCQE request) {
        List<MethodDisableDTO> result = repository.findAll();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> addOrEditMethodDisable(AddCommand<MethodDisableDTO> request) {
        MethodDisable methodDisable = new MethodDisable(request.getDto());
        methodDisable.saveMethodDisable(repository);
        return ServiceResult.buildSuccessResult(true);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> delMethodDisable(DelMethodDisableCommand request) {
        MethodDisable methodDisable = new MethodDisable(request);
        methodDisable.del(repository);
        return ServiceResult.buildSuccessResult(true);
    }
}
