package indi.uhyils.service;

import indi.uhyils.pojo.model.ApiSubscribeEntity;
import indi.uhyils.pojo.request.SubscribeRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

/**
 * api订阅表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface ApiSubscribeService extends DefaultEntityService<ApiSubscribeEntity> {

    /**
     * 订阅
     *
     * @param request 订阅请求
     * @return 是否订阅成功
     */
    ServiceResult<Boolean> subscribe(SubscribeRequest request) throws Exception;
}
