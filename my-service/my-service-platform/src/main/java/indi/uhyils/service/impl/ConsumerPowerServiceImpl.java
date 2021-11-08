package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ConsumerPowerAssembler;
import indi.uhyils.enum_.ConsumerStatusEnum;
import indi.uhyils.pojo.DO.ConsumerPowerDO;
import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.pojo.cqe.command.AgreeForInterfacePowerCommand;
import indi.uhyils.pojo.cqe.command.ApplyForInterfacePowerCommand;
import indi.uhyils.pojo.entity.ConsumerPower;
import indi.uhyils.repository.ConsumerPowerRepository;
import indi.uhyils.service.ConsumerPowerService;
import org.springframework.stereotype.Service;

/**
 * 消费方权限表(ConsumerPower)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
@Service
@ReadWriteMark(tables = {"sys_consumer_power"})
public class ConsumerPowerServiceImpl extends AbstractDoService<ConsumerPowerDO, ConsumerPower, ConsumerPowerDTO, ConsumerPowerRepository, ConsumerPowerAssembler> implements ConsumerPowerService {

    public ConsumerPowerServiceImpl(ConsumerPowerAssembler assembler, ConsumerPowerRepository repository) {
        super(assembler, repository);
    }


    @Override
    public Boolean applyForInterfacePower(ApplyForInterfacePowerCommand command) {
        ConsumerPower consumerPower = assem.toEntity(command);
        consumerPower.perInsert();
        consumerPower.checkRationality(rep);
        consumerPower.saveSelf(rep);
        return true;
    }

    @Override
    public Boolean agreeForInterfacePower(AgreeForInterfacePowerCommand command) {
        ConsumerPower consumerPower = assem.toEntity(command);
        consumerPower.checkStatus(rep, ConsumerStatusEnum.REGISTTING);
        consumerPower.changeToUsing();
        consumerPower.saveSelf(rep);
        return true;
    }
}
