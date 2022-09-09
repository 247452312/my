package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ProviderInterfaceHttpDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceHttpDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceHttp;
import org.mapstruct.Mapper;

/**
 * http接口子表(ProviderInterfaceHttp)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Mapper(componentModel = "spring")
public abstract class ProviderInterfaceHttpAssembler extends AbstractAssembler<ProviderInterfaceHttpDO, ProviderInterfaceHttp, ProviderInterfaceHttpDTO> {

}
