package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.DTO.request.base.IdsRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DefaultDTOProvider;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public interface OrderBaseNodeProvider extends DefaultDTOProvider<OrderBaseNodeDO> {


    /**
     * 批量删除,删除工单时用
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> deleteByIds(IdsRequest request);
}
