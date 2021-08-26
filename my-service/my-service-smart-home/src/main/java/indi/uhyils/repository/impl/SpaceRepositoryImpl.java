package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.SpaceConvert;
import indi.uhyils.dao.SpaceDao;
import indi.uhyils.pojo.entity.Space;
import indi.uhyils.pojo.DO.SpaceDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.SpaceRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 空间坐标表(Space)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分58秒
 */
@Repository
public class SpaceRepositoryImpl extends AbstractRepository<Space, SpaceDO, SpaceDao> implements SpaceRepository {

    protected SpaceRepositoryImpl(SpaceConvert convert, SpaceDao dao) {
        super(convert, dao);
    }


}
