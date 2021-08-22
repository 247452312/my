package indi.uhyils.service;

import indi.uhyils.pojo.model.OrderInfoDO;
import indi.uhyils.pojo.request.GetAllOrderRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.order.GetOneOrderResponse;
import indi.uhyils.service.base.DefaultDOService;
import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public interface OrderInfoService extends DefaultDOService<OrderInfoDO> {

    /**
     * 根据类型获取全部工单
     *
     * @param request
     *
     * @return
     */
    ServiceResult<ArrayList<OrderInfoDO>> getAllOrder(GetAllOrderRequest request);

    /**
     * 获取一个工单的所有信息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetOneOrderResponse> getOneOrder(IdRequest request);
}
