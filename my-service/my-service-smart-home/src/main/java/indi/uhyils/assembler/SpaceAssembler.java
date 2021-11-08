package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.SpaceDO;
import indi.uhyils.pojo.DTO.SpaceDTO;
import indi.uhyils.pojo.entity.Space;
import org.mapstruct.Mapper;

/**
 * 空间坐标表(Space)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分35秒
 */
@Mapper(componentModel = "spring")
public abstract class SpaceAssembler extends AbstractAssembler<SpaceDO, Space, SpaceDTO> {

}
