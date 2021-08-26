package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.SceneConvert;
import indi.uhyils.dao.SceneDao;
import indi.uhyils.pojo.entity.Scene;
import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.SceneRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 场景表(Scene)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分53秒
 */
@Repository
public class SceneRepositoryImpl extends AbstractRepository<Scene, SceneDO, SceneDao> implements SceneRepository {

    protected SceneRepositoryImpl(SceneConvert convert, SceneDao dao) {
        super(convert, dao);
    }


}
