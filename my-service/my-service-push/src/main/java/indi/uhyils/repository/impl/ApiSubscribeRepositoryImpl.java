package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ApiSubscribeAssembler;
import indi.uhyils.dao.ApiSubscribeDao;
import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.DTO.ApiSubscribeDTO;
import indi.uhyils.pojo.entity.ApiSubscribe;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ApiSubscribeRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;


/**
 * api订阅表(ApiSubscribe)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分54秒
 */
@Repository
public class ApiSubscribeRepositoryImpl extends AbstractRepository<ApiSubscribe, ApiSubscribeDO, ApiSubscribeDao, ApiSubscribeDTO, ApiSubscribeAssembler> implements ApiSubscribeRepository {

    protected ApiSubscribeRepositoryImpl(ApiSubscribeAssembler convert, ApiSubscribeDao dao) {
        super(convert, dao);
    }


    @Override
    public List<ApiSubscribe> findByGroupAndUserId(Identifier groupId, Identifier userId) {
        List<ApiSubscribeDO> apiSubscribeDOS = dao.getByGroupAndUserId(groupId.getId(), userId.getId());
        return assembler.listToEntity(apiSubscribeDOS);
    }
}
