package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.SoftwareDO;
import indi.uhyils.pojo.DTO.SoftwareDTO;
import indi.uhyils.pojo.entity.Software;

/**
 * 中间件表(Software)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分21秒
 */
@Assembler
public class SoftwareAssembler extends AbstractAssembler<SoftwareDO, Software, SoftwareDTO> {

    @Override
    public Software toEntity(SoftwareDO dO) {
        return new Software(dO);
    }

    @Override
    public Software toEntity(SoftwareDTO dto) {
        return new Software(toDo(dto));
    }

    @Override
    protected Class<SoftwareDO> getDoClass() {
        return SoftwareDO.class;
    }

    @Override
    protected Class<SoftwareDTO> getDtoClass() {
        return SoftwareDTO.class;
    }
}

