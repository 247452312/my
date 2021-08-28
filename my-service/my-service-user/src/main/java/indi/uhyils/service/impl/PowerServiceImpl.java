package indi.uhyils.service.impl;

import indi.uhyils.assembler.PowerAssembler;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.cqe.query.CheckUserHavePowerQuery;
import indi.uhyils.pojo.DTO.request.GetMethodNameByInterfaceNameQuery;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.PowerId;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
import indi.uhyils.pojo.entity.type.PowerInfo;
import indi.uhyils.pojo.entity.UserId;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.service.PowerService;
import indi.uhyils.util.ApiPowerInitUtil;
import indi.uhyils.util.LogUtil;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * 权限(Power)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分55秒
 */
@Service
public class PowerServiceImpl extends AbstractDoService<PowerDO, Power, PowerDTO, PowerRepository, PowerAssembler> implements PowerService {

    public PowerServiceImpl(PowerAssembler assembler, PowerRepository repository) {
        super(assembler, repository);
    }


    @Override
    public ServiceResult<List<PowerDTO>> getPowers(DefaultCQE request) {
        List<Power> powers = rep.getAll();
        List<PowerDTO> result = powers.stream().map(assem::toDTO).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("获取成功", result);
    }

    @Override
    public ServiceResult<Boolean> checkUserHavePower(CheckUserHavePowerQuery request) {
        UserId userId = new UserId(request.getUserId());
        PowerInfo powerInfo = new PowerInfo(request.getInterfaceName(), request.getMethodName());
        Boolean havePower = userId.havePower(powerInfo, rep);
        return ServiceResult.buildSuccessResult("查询成功", havePower);
    }

    @Override
    public ServiceResult<Boolean> deletePower(IdCommand request) {
        PowerId powerId = new PowerId(request.getId());
        powerId.removeSelfLink(rep);
        powerId.removeSelf(rep);
        return ServiceResult.buildSuccessResult("删除成功", Boolean.TRUE);
    }

    @Override
    public ServiceResult<List<String>> getInterfaces(DefaultCQE request) {
        List<String> interfaces = rep.getInterfaces();
        return ServiceResult.buildSuccessResult("查询成功", interfaces);
    }

    @Override
    public ServiceResult<List<String>> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameQuery request) {
        InterfaceName interfaceName = new InterfaceName(request.getInterfaceName());
        List<MethodName> methodName = interfaceName.getMethods(rep);
        return ServiceResult.buildSuccessResult("查询成功", methodName.stream().map(MethodName::getMethodName).collect(Collectors.toList()));
    }

    @Override
    public ServiceResult<Integer> initPowerInProStart(DefaultCQE request) throws Exception {
        List<PowerInfo> powersSingle;
        try {
            powersSingle = ApiPowerInitUtil.getPowersSingle();
        } catch (IOException e) {
            LogUtil.error(this, e);
            return ServiceResult.buildErrorResult("初始化power失败:" + e.getMessage());
        }
        int newPowerCount = 0;
        for (PowerInfo powerSimpleDO : powersSingle) {
            Boolean legitimate = powerSimpleDO.exist(rep);
            // 如果数据库中不存在此权限
            if (!legitimate) {
                Power power = powerSimpleDO.toEntity(assem);
                rep.save(power);
                newPowerCount++;
            }
        }
        return ServiceResult.buildSuccessResult("初始化power成功", newPowerCount);
    }
}
