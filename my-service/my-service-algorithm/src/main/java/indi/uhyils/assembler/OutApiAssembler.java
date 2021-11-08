package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.OutApiDO;
import indi.uhyils.pojo.DTO.OutApiDTO;
import indi.uhyils.pojo.entity.OutApi;
import org.mapstruct.Mapper;

/**
 * 开放api(OutApi)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分10秒
 */
@Mapper(componentModel = "spring")
public abstract class OutApiAssembler extends AbstractAssembler<OutApiDO, OutApi, OutApiDTO> {

}

