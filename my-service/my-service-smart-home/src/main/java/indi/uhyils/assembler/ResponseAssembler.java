package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ResponseDO;
import indi.uhyils.pojo.DTO.ResponseDTO;
import indi.uhyils.pojo.entity.Response;
import org.mapstruct.Mapper;

/**
 * 设备指令回应表(Response)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分27秒
 */
@Mapper(componentModel = "spring")
public abstract class ResponseAssembler extends AbstractAssembler<ResponseDO, Response, ResponseDTO> {

}
