package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.pojo.DTO.ApiGroupDTO;
import indi.uhyils.pojo.entity.ApiGroup;
import org.mapstruct.Mapper;

/**
 * api组表(ApiGroup)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分49秒
 */
@Mapper(componentModel = "spring")
public abstract class ApiGroupAssembler extends AbstractAssembler<ApiGroupDO, ApiGroup, ApiGroupDTO> {

}
