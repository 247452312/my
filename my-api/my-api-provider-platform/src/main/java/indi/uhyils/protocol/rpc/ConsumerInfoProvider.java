package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.ConsumerRegisterCommand;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 服务消费方信息表(ConsumerInfo)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分39秒
 */
public interface ConsumerInfoProvider extends DTOProvider<ConsumerInfoDTO> {


    /**
     * 消费者注册
     *
     * @param command
     *
     * @return
     */
    ServiceResult<ConsumerInfoDTO> consumerRegister(ConsumerRegisterCommand command);
}

