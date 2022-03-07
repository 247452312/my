package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PowerAssembler;
import indi.uhyils.context.UserContext;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
import indi.uhyils.pojo.entity.type.PowerInfo;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.service.PowerService;
import indi.uhyils.util.ApiPowerInitUtil;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限(Power)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分55秒
 */
@Service
@ReadWriteMark(tables = {"sys_power"})
public class PowerServiceImpl extends AbstractDoService<PowerDO, Power, PowerDTO, PowerRepository, PowerAssembler> implements PowerService {

    public PowerServiceImpl(PowerAssembler assembler, PowerRepository repository) {
        super(assembler, repository);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PowerDTO> getPowers() {
        List<Power> powers = rep.getAll();
        return powers.stream().map(assem::toDTO).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_user", "sys_role", "sys_role_dept", "sys_dept", "sys_dept_power", "sys_power"})
    public Boolean checkUserHavePower(InterfaceName interfaceName, MethodName methodName, Identifier userId) {
        User user = userRepository.findUserByIdInRedis(userId);

        PowerInfo powerInfo = new PowerInfo(interfaceName.getInterfaceName(), methodName.getMethodName());
        return user.havePower(powerInfo, rep);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_power"})
    public Boolean deletePower(Identifier powerId) {
        Power power = new Power(powerId);
        power.removeSelfLink(rep);
        power.removeSelf(rep);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_power"})
    public List<String> getInterfaces() {
        return rep.getInterfaces();
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_power"})
    public List<String> getMethodNameByInterfaceName(InterfaceName interfaceName) {
        InterfaceName name = new InterfaceName(interfaceName.getInterfaceName());
        List<MethodName> methodName = name.getMethods(rep);
        return methodName.stream().map(MethodName::getMethodName).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_power"})
    public Integer initPowerInProStart() {
        List<PowerInfo> powersSingle = null;
        try {
            powersSingle = ApiPowerInitUtil.getPowersSingle();
        } catch (IOException e) {
            LogUtil.error(this, e);
            Asserts.assertTrue(false, "初始化power失败:" + e.getMessage());
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
        return newPowerCount;
    }
}
