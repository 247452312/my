package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.order.GetOneBaseOrderResponse;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public interface OrderBaseInfoProvider extends DTOProvider<OrderBaseInfoDO> {

    /**
     * 获取全部的基础工单(id与名称)
     *
     * @param request
     *
     * @return
     */
    ServiceResult<ArrayList<OrderBaseInfoDO>> getAllBaseOrderIdAndName(DefaultRequest request);

    /**
     * 获取一个工单的所有信息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetOneBaseOrderResponse> getOneOrder(IdRequest request);

}
