package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.DTO.ApiSubscribeDTO;
import indi.uhyils.pojo.DTO.request.SubscribeRequest;
import indi.uhyils.pojo.entity.ApiSubscribe;

/**
 * api订阅表(ApiSubscribe)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分53秒
 */
@Assembler
public class ApiSubscribeAssembler extends AbstractAssembler<ApiSubscribeDO, ApiSubscribe, ApiSubscribeDTO> {

    @Override
    public ApiSubscribe toEntity(ApiSubscribeDO dO) {
        return new ApiSubscribe(dO);
    }

    @Override
    public ApiSubscribe toEntity(ApiSubscribeDTO dto) {
        return new ApiSubscribe(toDo(dto));
    }

    @Override
    protected Class<ApiSubscribeDO> getDoClass() {
        return ApiSubscribeDO.class;
    }

    @Override
    protected Class<ApiSubscribeDTO> getDtoClass() {
        return ApiSubscribeDTO.class;
    }

    public ApiSubscribeDTO toDTO(SubscribeRequest request) {
        return ApiSubscribeDTO.build(request);
    }
}

