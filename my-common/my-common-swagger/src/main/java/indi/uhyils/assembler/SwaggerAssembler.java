package indi.uhyils.assembler;


import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.entity.ClassSwaggerEntity;
import org.mapstruct.Mapper;

/**
 * 公共参数(Content)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分15秒
 */
@Mapper(componentModel = "spring")
public abstract class SwaggerAssembler implements BaseAssembler {


    public ClassSwaggerDTO toDTO(ClassSwaggerEntity entity) {
        return entity.toDTO();
    }

}
