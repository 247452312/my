package indi.uhyils.assembler;


import org.mapstruct.Mapper;
import indi.uhyils.pojo.DO.ProviderInfoDO;
import indi.uhyils.pojo.DTO.ProviderInfoDTO;
import indi.uhyils.pojo.entity.ProviderInfo;

/**
 * 服务提供者表(ProviderInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@Mapper(componentModel = "spring")
public abstract class ProviderInfoAssembler extends AbstractAssembler<ProviderInfoDO, ProviderInfo, ProviderInfoDTO> {

}

