package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DTO.ApiDTO;
import indi.uhyils.pojo.entity.Api;

/**
 * api表(Api)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分45秒
 */
@Assembler
public class ApiAssembler extends AbstractAssembler<ApiDO, Api, ApiDTO> {

    @Override
    public Api toEntity(ApiDO dO) {
        return new Api(dO);
    }

    @Override
    public Api toEntity(ApiDTO dto) {
        return new Api(toDo(dto));
    }

    @Override
    protected Class<ApiDO> getDoClass() {
        return ApiDO.class;
    }

    @Override
    protected Class<ApiDTO> getDtoClass() {
        return ApiDTO.class;
    }
}

