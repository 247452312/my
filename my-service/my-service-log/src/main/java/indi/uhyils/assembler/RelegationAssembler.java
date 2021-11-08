package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.RelegationDO;
import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.entity.Relegation;
import org.mapstruct.Mapper;

/**
 * 接口降级策略(Relegation)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分23秒
 */
@Mapper(componentModel = "spring")
public abstract class RelegationAssembler extends AbstractAssembler<RelegationDO, Relegation, RelegationDTO> {

}

