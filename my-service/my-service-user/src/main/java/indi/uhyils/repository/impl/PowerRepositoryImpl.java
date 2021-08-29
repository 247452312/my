package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PowerAssembler;
import indi.uhyils.dao.PowerDao;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.cqe.Arg;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.PowerId;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
import indi.uhyils.pojo.entity.type.PowerInfo;
import indi.uhyils.pojo.entity.UserId;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class PowerRepositoryImpl extends AbstractRepository<Power, PowerDO, PowerDao, PowerDTO, PowerAssembler> implements PowerRepository {


    protected PowerRepositoryImpl(PowerAssembler assembler, PowerDao dao) {
        super(assembler, dao);
    }

    @Override
    public List<Power> findByDeptId(Identifier deptId) {
        ArrayList<PowerDO> powerDos = dao.getByArgsNoPage(Arrays.asList(new Arg("dept_id", "=", deptId.getId())), null);
        return powerDos.stream().map(Power::new).collect(Collectors.toList());
    }

    @Override
    public List<Power> getAll() {
        ArrayList<PowerDO> all = dao.getAll();
        return all.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public Boolean havePower(UserId userId, PowerInfo powerInfo) {
        return dao.checkUserHavePower(userId.getId(), powerInfo.getInterfaceName(), powerInfo.getMethodName()) != 0;
    }

    @Override
    public void removeDeptPowerByPowerId(PowerId powerId) {
        dao.deleteDeptPowerMiddleByPowerId(powerId.powerIdValue());
    }

    @Override
    public List<String> getInterfaces() {
        return dao.getInterfaces();
    }

    @Override
    public List<MethodName> getMethodNameByInterfaceName(InterfaceName interfaceName) {
        ArrayList<String> methodNameByInterfaceName = dao.getMethodNameByInterfaceName(interfaceName.getInterfaceName());
        return methodNameByInterfaceName.stream().map(MethodName::new).collect(Collectors.toList());
    }

    @Override
    public Boolean exist(PowerInfo powerInfo) {
        return dao.checkPower(powerInfo.getInterfaceName(), powerInfo.getMethodName()) != 0;
    }
}
