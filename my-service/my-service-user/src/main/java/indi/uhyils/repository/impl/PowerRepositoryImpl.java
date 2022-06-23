package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PowerAssembler;
import indi.uhyils.dao.PowerDao;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.entity.User;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
import indi.uhyils.pojo.entity.type.PowerInfo;
import indi.uhyils.repository.PowerRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
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
        List<PowerDO> powerDOS = dao.getByDept(deptId.getId());
        return assembler.listToEntity(powerDOS);
    }

    @Override
    public List<Power> getAll() {
        ArrayList<PowerDO> all = dao.getAll();
        return all.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public Boolean havePower(User userId, PowerInfo powerInfo) {
        return dao.checkUserHavePower(
            userId.getUnique().map(Identifier::getId).orElseThrow(() -> Asserts.makeException("未找到用户id")),
            powerInfo.getInterfaceName(),
            powerInfo.getMethodName())
               != 0;
    }

    @Override
    public void removeDeptPowerByPowerId(Power powerId) {
        dao.deleteDeptPowerMiddleByPowerId(powerId.getUnique().map(Identifier::getId).orElseThrow(() -> Asserts.makeException("未找到权限id")));
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
