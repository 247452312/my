package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ApiAssembler;
import indi.uhyils.dao.ApiDao;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DO.ApiGroupDO;
import indi.uhyils.pojo.DTO.ApiDTO;
import indi.uhyils.pojo.entity.Api;
import indi.uhyils.pojo.entity.ApiGroup;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ApiRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;
import java.util.List;


/**
 * api表(Api)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分46秒
 */
@Repository
public class ApiRepositoryImpl extends AbstractRepository<Api, ApiDO, ApiDao, ApiDTO, ApiAssembler> implements ApiRepository {

    protected ApiRepositoryImpl(ApiAssembler convert, ApiDao dao) {
        super(convert, dao);
    }


    @Override
    public List<Api> findByGroupId(Identifier groupId) {
        List<ApiDO> groupByGroupId = dao.getGroupByGroupId(groupId.getId());
        return assembler.listToEntity(groupByGroupId);
    }

    @Override
    public Integer removeApiByGroup(ApiGroup groupId) {
        ApiGroupDO apiGroupDO = groupId.toData().orElseThrow(Asserts::throwOptionalException);
        apiGroupDO.preUpdate();
        return dao.deleteAllByGroup(apiGroupDO);
    }

    @Override
    public List<Api> findAll() {
        List<ApiDO> all = dao.getAll();
        return assembler.listToEntity(all);
    }
}
