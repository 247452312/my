package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.pojo.DTO.SceneDTO;
import indi.uhyils.pojo.entity.Scene;
import org.mapstruct.Mapper;

/**
 * 场景表(Scene)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分31秒
 */
@Mapper(componentModel = "spring")
public abstract class SceneAssembler extends AbstractAssembler<SceneDO, Scene, SceneDTO> {

}
