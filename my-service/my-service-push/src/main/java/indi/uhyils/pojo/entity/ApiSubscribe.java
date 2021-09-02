package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ApiSubscribeRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.CollectionUtil;
import java.util.List;

/**
 * api订阅表(ApiSubscribe)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 19时46分53秒
 */
public class ApiSubscribe extends AbstractDoEntity<ApiSubscribeDO> {

    public ApiSubscribe(ApiSubscribeDO dO) {
        super(dO);
    }

    public ApiSubscribe(Long id) {
        super(id, new ApiSubscribeDO());
    }

    public ApiSubscribe(Long id, ApiSubscribeRepository rep) {
        super(id, new ApiSubscribeDO());
        completion(rep);
    }

    public void checkRepeat(ApiSubscribeRepository rep) {
        List<ApiSubscribe> subscribes = rep.findByGroupAndUserId(new Identifier(toDo().getApiGroupId()), new Identifier(toDo().getUserId()));
        AssertUtil.assertTrue(CollectionUtil.isEmpty(subscribes),"不能同时订阅同一个api两次");
    }
}
