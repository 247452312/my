package indi.uhyils.service;

import indi.uhyils.pojo.model.OrderBaseNodeDO;
import indi.uhyils.pojo.request.base.IdsRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultDOService;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public interface OrderBaseNodeService extends DefaultDOService<OrderBaseNodeDO> {


    /**
     * 批量删除,删除工单时用
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> deleteByIds(IdsRequest request);
}
