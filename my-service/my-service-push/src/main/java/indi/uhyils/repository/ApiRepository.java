package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.entity.Api;
import indi.uhyils.pojo.entity.ApiGroup;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * api表(Api)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分46秒
 */
public interface ApiRepository extends BaseEntityRepository<ApiDO, Api> {


    /**
     * 根据groupId获取
     *
     * @param groupId
     *
     * @return
     */
    List<Api> findByGroupId(Identifier groupId);

    /**
     * 根据groupId删除
     *
     * @param groupId
     */
    Integer removeApiByGroup(ApiGroup groupId);

    /**
     * 获取全部
     *
     * @return
     */
    List<Api> findAll();
}
