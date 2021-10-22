package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.ApplyForInterfacePowerCommand;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 消费方权限表(ConsumerPower)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分39秒
 */
public interface ConsumerPowerProvider extends DTOProvider<ConsumerPowerDTO> {

    /**
     * 申请申请单权限
     *
     * @param command
     *
     * @return
     */
    ServiceResult<Boolean> applyForInterfacePower(ApplyForInterfacePowerCommand command);
}

