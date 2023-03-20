package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.MySwagger;
import indi.uhyils.annotation.Public;
import indi.uhyils.assembler.SwaggerAssembler;
import indi.uhyils.enums.ProtocolTypeEnum;
import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.cqe.FindSwaggerQuery;
import indi.uhyils.pojo.entity.SwaggerEntity;
import indi.uhyils.protocol.rpc.SwaggerProvider;
import indi.uhyils.rpc.annotation.RpcService;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

/**
 * swagger信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时32分
 */
@RpcService
@MySwagger(value = ProtocolTypeEnum.RPC, desc = "swagger详情获取")
public class SwaggerProviderImpl implements SwaggerProvider {

    @Resource
    private SwaggerAssembler assembler;

    @Override
    @Public
    public List<ClassSwaggerDTO> findSwagger(FindSwaggerQuery query) {
        SwaggerEntity entity = new SwaggerEntity();
        return entity.transClassSwagger().stream().map(t -> assembler.toDTO(t)).collect(Collectors.toList());
    }
}
