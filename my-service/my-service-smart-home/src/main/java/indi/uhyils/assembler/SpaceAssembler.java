package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.SpaceDO;
import indi.uhyils.pojo.DTO.SpaceDTO;
import indi.uhyils.pojo.entity.Space;

/**
 * 空间坐标表(Space)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分35秒
 */
@Assembler
public class SpaceAssembler extends AbstractAssembler<SpaceDO, Space, SpaceDTO> {

    @Override
    public Space toEntity(SpaceDO dO) {
        return new Space(dO);
    }

    @Override
    public Space toEntity(SpaceDTO dto) {
        return new Space(toDo(dto));
    }

    @Override
    protected Class<SpaceDO> getDoClass() {
        return SpaceDO.class;
    }

    @Override
    protected Class<SpaceDTO> getDtoClass() {
        return SpaceDTO.class;
    }
}

