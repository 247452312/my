package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.pojo.DTO.SceneDTO;
import indi.uhyils.pojo.entity.Scene;

/**
 * 场景表(Scene)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分51秒
 */
@Assembler
public class SceneAssembler extends AbstractAssembler<SceneDO, Scene, SceneDTO> {

    @Override
    public Scene toEntity(SceneDO dO) {
        return new Scene(dO);
    }

    @Override
    public Scene toEntity(SceneDTO dto) {
        return new Scene(toDo(dto));
    }

    @Override
    protected Class<SceneDO> getDoClass() {
        return SceneDO.class;
    }

    @Override
    protected Class<SceneDTO> getDtoClass() {
        return SceneDTO.class;
    }
}

