package indi.uhyils.assembler;


import org.mapstruct.Mapper;
import indi.uhyils.pojo.DO.ConsumerPowerDO;
import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.pojo.entity.ConsumerPower;

/**
 * 消费方权限表(ConsumerPower)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分05秒
 */
@Mapper(componentModel = "spring")
public abstract class ConsumerPowerAssembler extends AbstractAssembler<ConsumerPowerDO, ConsumerPower, ConsumerPowerDTO> {

}

