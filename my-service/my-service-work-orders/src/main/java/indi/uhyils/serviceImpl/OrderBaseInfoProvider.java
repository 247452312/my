package indi.uhyils.serviceImpl;

import indi.uhyils.dao.*;
import indi.uhyils.pojo.DO.*;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.order.GetOneBaseOrderResponse;
import indi.uhyils.pojo.DTO.response.order.OrderBaseNodeAboutResponse;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@RpcService
public class OrderBaseInfoProvider extends BaseDefaultProvider<OrderBaseInfoDO> implements indi.uhyils.protocol.rpc.provider.OrderBaseInfoProvider {

    @Resource
    private OrderBaseInfoDao dao;

    @Resource
    private OrderBaseNodeDao orderBaseNodeDao;

    @Resource
    private OrderBaseNodeFieldDao orderBaseNodeFieldDao;

    @Resource
    private OrderBaseNodeResultTypeDao orderBaseNodeResultTypeDao;

    @Resource
    private OrderBaseNodeRouteDao orderBaseNodeRouteDao;


    @Override
    public OrderBaseInfoDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseInfoDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<ArrayList<OrderBaseInfoDO>> getAllBaseOrderIdAndName(DefaultRequest request) {
        ArrayList<OrderBaseInfoDO> result = dao.getAllBaseOrderIdAndName();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<GetOneBaseOrderResponse> getOneOrder(IdRequest request) {
        Long id = request.getId();
        OrderBaseInfoDO byId = dao.getById(id);
        List<OrderBaseNodeDO> orderBaseNodes = orderBaseNodeDao.getNoHiddenByOrderId(id);
        List<Long> orderNodeIds = orderBaseNodes.stream().map(BaseIdDO::getId).collect(Collectors.toList());
        Map<Long, List<OrderBaseNodeFieldDO>> orderBaseFieldMap = orderBaseNodeFieldDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderBaseNodeFieldDO::getBaseOrderId));
        Map<Long, List<OrderBaseNodeResultTypeDO>> orderBaseNodeResultTypeMap = orderBaseNodeResultTypeDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderBaseNodeResultTypeDO::getBaseNodeId));
        Map<Long, List<OrderBaseNodeRouteDO>> orderBaseNodeRouteMap = orderBaseNodeRouteDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderBaseNodeRouteDO::getPrevNodeId));
        ArrayList<OrderBaseNodeAboutResponse> orderBaseNodeList = new ArrayList<>();
        for (OrderBaseNodeDO orderBaseNode : orderBaseNodes) {
            Long nodeId = orderBaseNode.getId();
            OrderBaseNodeAboutResponse build = OrderBaseNodeAboutResponse.build(orderBaseNode, orderBaseFieldMap.get(nodeId), orderBaseNodeResultTypeMap.get(nodeId), orderBaseNodeRouteMap.get(nodeId));
            orderBaseNodeList.add(build);
        }
        GetOneBaseOrderResponse build = GetOneBaseOrderResponse.build(byId, orderBaseNodeList);
        return ServiceResult.buildSuccessResult(build);
    }
}
