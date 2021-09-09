package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OutApiDO;
import indi.uhyils.pojo.DTO.OutApiDTO;
import indi.uhyils.pojo.entity.OutApi;

/**
 * 开放api(OutApi)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分10秒
 */
@Assembler
public class OutApiAssembler extends AbstractAssembler<OutApiDO, OutApi, OutApiDTO> {

    @Override
    public OutApi toEntity(OutApiDO dO) {
        return new OutApi(dO);
    }

    @Override
    public OutApi toEntity(OutApiDTO dto) {
        return new OutApi(toDo(dto));
    }

    @Override
    protected Class<OutApiDO> getDoClass() {
        return OutApiDO.class;
    }

    @Override
    protected Class<OutApiDTO> getDtoClass() {
        return OutApiDTO.class;
    }
}

