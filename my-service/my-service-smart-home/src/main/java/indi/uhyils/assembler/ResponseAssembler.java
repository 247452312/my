package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.ResponseDO;
import indi.uhyils.pojo.DTO.ResponseDTO;
import indi.uhyils.pojo.entity.Response;

/**
 * 设备指令回应表(Response)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分27秒
 */
@Assembler
public class ResponseAssembler extends AbstractAssembler<ResponseDO, Response, ResponseDTO> {

    @Override
    public Response toEntity(ResponseDO dO) {
        return new Response(dO);
    }

    @Override
    public Response toEntity(ResponseDTO dto) {
        return new Response(toDo(dto));
    }

    @Override
    protected Class<ResponseDO> getDoClass() {
        return ResponseDO.class;
    }

    @Override
    protected Class<ResponseDTO> getDtoClass() {
        return ResponseDTO.class;
    }
}

