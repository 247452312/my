package indi.uhyils.assembler;


import org.mapstruct.Mapper;
import indi.uhyils.pojo.DO.ConsumerInfoDO;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.entity.ConsumerInfo;

/**
 * 服务消费方信息表(ConsumerInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@Mapper(componentModel = "spring")
public abstract class ConsumerInfoAssembler extends AbstractAssembler<ConsumerInfoDO, ConsumerInfo, ConsumerInfoDTO> {

}

