package indi.uhyils.assembler;


import org.mapstruct.Mapper;
import indi.uhyils.pojo.DO.ConsumerFilterDO;
import indi.uhyils.pojo.DTO.ConsumerFilterDTO;
import indi.uhyils.pojo.entity.ConsumerFilter;

/**
 * 消费过滤表(ConsumerFilter)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分03秒
 */
@Mapper(componentModel = "spring")
public abstract class ConsumerFilterAssembler extends AbstractAssembler<ConsumerFilterDO, ConsumerFilter, ConsumerFilterDTO> {

}

