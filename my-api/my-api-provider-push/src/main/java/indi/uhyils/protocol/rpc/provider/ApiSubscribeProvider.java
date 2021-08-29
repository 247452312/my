package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.DTO.request.SubscribeRequest;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * api订阅表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface ApiSubscribeProvider extends DTOProvider<ApiSubscribeDO> {

    /**
     * 订阅
     *
     * @param request 订阅请求
     *
     * @return 是否订阅成功
     *
     * @throws InterruptedException
     */
    ServiceResult<Boolean> subscribe(SubscribeRequest request);
}
