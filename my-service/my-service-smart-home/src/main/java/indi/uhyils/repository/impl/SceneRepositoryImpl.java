package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.SceneConvert;
import indi.uhyils.dao.SceneDao;
import indi.uhyils.pojo.entity.Scene;
import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.repository.SceneRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 场景表(Scene)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分29秒
 */
@Repository
public class SceneRepositoryImpl extends AbstractRepository<Scene, SceneDO, SceneDao> implements SceneRepository {

    protected SceneRepositoryImpl(SceneAssembler convert, SceneDao dao) {
        super(convert, dao);
    }


}
