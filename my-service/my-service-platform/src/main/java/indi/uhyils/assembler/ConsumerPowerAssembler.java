package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ConsumerPowerDO;
import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.pojo.cqe.command.AgreeForInterfacePowerCommand;
import indi.uhyils.pojo.cqe.command.ApplyForInterfacePowerCommand;
import indi.uhyils.pojo.entity.ConsumerPower;
import org.mapstruct.Mapper;

/**
 * 消费方权限表(ConsumerPower)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分05秒
 */
@Mapper(componentModel = "spring")
public abstract class ConsumerPowerAssembler extends AbstractAssembler<ConsumerPowerDO, ConsumerPower, ConsumerPowerDTO> {

    /**
     * 转换
     *
     * @param command
     *
     * @return
     */
    public ConsumerPower toEntity(ApplyForInterfacePowerCommand command) {
        ConsumerPowerDO consumerPowerDO = toDo(command);
        return new ConsumerPower(consumerPowerDO);
    }

    /**
     * 转换为DO
     *
     * @param command
     *
     * @return
     */
    public abstract ConsumerPowerDO toDo(ApplyForInterfacePowerCommand command);

    /**
     * 转换为DO
     *
     * @param command
     *
     * @return
     */
    public abstract ConsumerPowerDO toDo(AgreeForInterfacePowerCommand command);

    /**
     * 转换入参
     *
     * @param command
     *
     * @return
     */
    public ConsumerPower toEntity(AgreeForInterfacePowerCommand command) {
        ConsumerPowerDO consumerPowerDO = toDo(command);
        return new ConsumerPower(consumerPowerDO);
    }
}
