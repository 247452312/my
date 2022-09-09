package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceDTO;
import indi.uhyils.pojo.entity.ProviderInterface;
import org.mapstruct.Mapper;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Mapper(componentModel = "spring")
public abstract class ProviderInterfaceAssembler extends AbstractAssembler<ProviderInterfaceDO, ProviderInterface, ProviderInterfaceDTO> {

}
