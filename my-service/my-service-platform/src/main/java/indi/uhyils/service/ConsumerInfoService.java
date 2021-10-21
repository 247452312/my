package indi.uhyils.service;


import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.cqe.command.ConsumerRegisterCommand;

/**
 * 服务消费方信息表(ConsumerInfo)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
public interface ConsumerInfoService extends BaseDoService<ConsumerInfoDTO> {

    /**
     * 消费者注册
     *
     * @param command
     *
     * @return
     */
    ConsumerInfoDTO consumerRegister(ConsumerRegisterCommand command);
}
