package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.SceneAssembler;
import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.pojo.DTO.SceneDTO;
import indi.uhyils.pojo.entity.Scene;
import indi.uhyils.repository.SceneRepository;
import indi.uhyils.service.SceneService;
import org.springframework.stereotype.Service;

/**
 * 场景表(Scene)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分32秒
 */
@Service
@ReadWriteMark(tables = {"sys_scene"})
public class SceneServiceImpl extends AbstractDoService<SceneDO, Scene, SceneDTO, SceneRepository, SceneAssembler> implements SceneService {

    public SceneServiceImpl(SceneAssembler assembler, SceneRepository repository) {
        super(assembler, repository);
    }


}
