package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ConsumerInfoDO;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.cqe.command.ConsumerRegisterCommand;
import indi.uhyils.pojo.entity.ConsumerInfo;
import org.mapstruct.Mapper;

/**
 * 服务消费方信息表(ConsumerInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@Mapper(componentModel = "spring")
public abstract class ConsumerInfoAssembler extends AbstractAssembler<ConsumerInfoDO, ConsumerInfo, ConsumerInfoDTO> {

    /**
     * 入参转换
     *
     * @param command
     *
     * @return
     */
    public ConsumerInfo toEntity(ConsumerRegisterCommand command) {
        ConsumerInfoDO consumerInfoDO = toDo(command);
        return new ConsumerInfo(consumerInfoDO);
    }

    public abstract ConsumerInfoDO toDo(ConsumerRegisterCommand command);
}

