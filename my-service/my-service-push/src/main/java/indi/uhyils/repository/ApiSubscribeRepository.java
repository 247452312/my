package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.entity.ApiSubscribe;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * api订阅表(ApiSubscribe)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分54秒
 */
public interface ApiSubscribeRepository extends BaseEntityRepository<ApiSubscribeDO, ApiSubscribe> {


    /**
     * 获取订阅表
     *
     * @param groupId
     * @param userId
     *
     * @return
     */
    List<ApiSubscribe> findByGroupAndUserId(Identifier groupId, Identifier userId);
}
