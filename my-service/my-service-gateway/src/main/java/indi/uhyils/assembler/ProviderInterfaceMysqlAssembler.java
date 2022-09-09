package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ProviderInterfaceMysqlDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceMysqlDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceMysql;
import org.mapstruct.Mapper;

/**
 * mysql接口子表(ProviderInterfaceMysql)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Mapper(componentModel = "spring")
public abstract class ProviderInterfaceMysqlAssembler extends AbstractAssembler<ProviderInterfaceMysqlDO, ProviderInterfaceMysql, ProviderInterfaceMysqlDTO> {

}
