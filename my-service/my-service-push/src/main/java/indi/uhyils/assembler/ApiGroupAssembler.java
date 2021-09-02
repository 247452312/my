package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.pojo.DTO.ApiGroupDTO;
import indi.uhyils.pojo.entity.ApiGroup;

/**
 * api组表(ApiGroup)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分49秒
 */
@Assembler
public class ApiGroupAssembler extends AbstractAssembler<ApiGroupDO, ApiGroup, ApiGroupDTO> {

    @Override
    public ApiGroup toEntity(ApiGroupDO dO) {
        return new ApiGroup(dO);
    }

    @Override
    public ApiGroup toEntity(ApiGroupDTO dto) {
        return new ApiGroup(toDo(dto));
    }

    @Override
    protected Class<ApiGroupDO> getDoClass() {
        return ApiGroupDO.class;
    }

    @Override
    protected Class<ApiGroupDTO> getDtoClass() {
        return ApiGroupDTO.class;
    }
}

