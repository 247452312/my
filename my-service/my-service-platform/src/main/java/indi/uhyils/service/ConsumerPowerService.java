package indi.uhyils.service;


import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.pojo.cqe.command.AgreeForInterfacePowerCommand;
import indi.uhyils.pojo.cqe.command.ApplyForInterfacePowerCommand;

/**
 * 消费方权限表(ConsumerPower)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分06秒
 */
public interface ConsumerPowerService extends BaseDoService<ConsumerPowerDTO> {

    /**
     * 申请申请单权限
     *
     * @param command
     *
     * @return
     */
    Boolean applyForInterfacePower(ApplyForInterfacePowerCommand command);


    /**
     * 同意申请单
     *
     * @param command
     *
     * @return
     */
    Boolean agreeForInterfacePower(AgreeForInterfacePowerCommand command);
}
