package indi.uhyils.facade.impl;

import indi.uhyils.annotation.Facade;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.enums.ReadWriteTypeEnum;
import indi.uhyils.facade.ServiceControlFacade;
import indi.uhyils.pojo.DTO.MethodDisableDTO;
import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.DelMethodDisableCommand;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
import indi.uhyils.protocol.rpc.ServiceControlProvider;
import indi.uhyils.rpc.annotation.RpcReference;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月28日 16时09分
 */
@Facade
public class ServiceControlFacadeImpl implements ServiceControlFacade {

    @RpcReference
    private ServiceControlProvider provider;

    @Override
    public boolean demotion(String serviceName, String methodName) {
        AddCommand<MethodDisableDTO> request = new AddCommand<>();
        request.setDto(MethodDisableDTO.build(serviceName, methodName, ReadWriteTypeEnum.READ.getCode()));
        ServiceResult<Boolean> result = provider.addOrEditMethodDisable(request);
        return result.validationAndGet();
    }

    @Override
    public boolean recover(String serviceName, String methodName) {
        DelMethodDisableCommand request = new DelMethodDisableCommand();
        request.setClassName(serviceName);
        request.setMethodName(methodName);
        ServiceResult<Boolean> result = provider.delMethodDisable(request);
        return result.validationAndGet();
    }

    @Override
    public void fillDisable(List<RelegationDTO> dtos) {
        ServiceResult<List<MethodDisableDTO>> allMethodDisable = provider.getAllMethodDisable(UserInfoHelper.makeCQE());
        List<MethodDisableDTO> result = allMethodDisable.validationAndGet();
        for (RelegationDTO dto : dtos) {
            for (MethodDisableDTO disableDTO : result) {
                String className = disableDTO.getClassName();
                String methodName = disableDTO.getMethodName();
                if (Objects.equals(dto.getServiceName(), className) && Objects.equals(dto.getMethodName(), methodName)) {
                    Integer disableType = disableDTO.getDisableType();
                    dto.setDisable(disableType != 1);
                    break;
                }
            }
        }
    }

    @Override
    public void fillDisable(RelegationDTO dto) {
        fillDisable(Arrays.asList(dto));
    }
}
