package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.SceneAssembler;
import indi.uhyils.dao.SceneDao;
import indi.uhyils.pojo.entity.Scene;
import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.pojo.DTO.SceneDTO;
import indi.uhyils.repository.SceneRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 场景表(Scene)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分32秒
 */
@Repository
public class SceneRepositoryImpl extends AbstractRepository<Scene, SceneDO, SceneDao, SceneDTO, SceneAssembler> implements SceneRepository {

    protected SceneRepositoryImpl(SceneAssembler convert, SceneDao dao) {
        super(convert, dao);
    }


}
