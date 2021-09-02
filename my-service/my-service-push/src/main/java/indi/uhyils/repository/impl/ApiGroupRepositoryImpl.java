package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ApiGroupAssembler;
import indi.uhyils.dao.ApiGroupDao;
import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.pojo.DTO.ApiGroupDTO;
import indi.uhyils.pojo.entity.ApiGroup;
import indi.uhyils.repository.ApiGroupRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;


/**
 * api组表(ApiGroup)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分50秒
 */
@Repository
public class ApiGroupRepositoryImpl extends AbstractRepository<ApiGroup, ApiGroupDO, ApiGroupDao, ApiGroupDTO, ApiGroupAssembler> implements ApiGroupRepository {

    protected ApiGroupRepositoryImpl(ApiGroupAssembler convert, ApiGroupDao dao) {
        super(convert, dao);
    }


    @Override
    public List<ApiGroup> getCanBeSubscribed() {
        List<ApiGroupDO> canBeSubscribed = dao.getCanBeSubscribed();
        return assembler.listToEntity(canBeSubscribed);
    }
}
