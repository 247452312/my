package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DTO.request.GetAllOrderQuery;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.order.GetOneOrderResponse;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public interface OrderInfoProvider extends DTOProvider<OrderInfoDO> {

    /**
     * 根据类型获取全部工单
     *
     * @param request
     *
     * @return
     */
    ServiceResult<ArrayList<OrderInfoDO>> getAllOrder(GetAllOrderQuery request);

    /**
     * 获取一个工单的所有信息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetOneOrderResponse> getOneOrder(IdRequest request);
}
