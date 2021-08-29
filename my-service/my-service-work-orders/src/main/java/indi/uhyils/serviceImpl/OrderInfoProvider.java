package indi.uhyils.serviceImpl;

import indi.uhyils.dao.*;
import indi.uhyils.pojo.DO.*;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.request.GetAllOrderQuery;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.order.GetOneOrderResponse;
import indi.uhyils.pojo.DTO.response.order.OrderNodeAboutResponse;
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
public class OrderInfoProvider extends BaseDefaultProvider<OrderInfoDO> implements indi.uhyils.protocol.rpc.provider.OrderInfoProvider {

    @Resource
    private OrderInfoDao dao;

    @Resource
    private OrderNodeDao orderNodeDao;

    @Resource
    private OrderNodeFieldDao orderNodeFieldDao;

    @Resource
    private OrderNodeResultTypeDao orderNodeResultTypeDao;

    @Resource
    private OrderNodeRouteDao orderNodeRouteDao;


    @Resource
    private OrderInfoDao orderInfoDao;


    @Resource
    private OrderNodeFieldValueDao orderNodeFieldValueDao;


    @Override
    public OrderInfoDao getDao() {
        return dao;
    }

    public void setDao(OrderInfoDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<ArrayList<OrderInfoDO>> getAllOrder(GetAllOrderQuery request) {
        ArrayList<OrderInfoDO> result = dao.getOrderByType(request.getType());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<GetOneOrderResponse> getOneOrder(IdRequest request) {
        Long id = request.getId();
        OrderInfoDO byId = dao.getById(id);
        List<OrderNodeDO> orderNodes = orderNodeDao.getByOrderId(id);
        List<Long> orderNodeIds = orderNodes.stream().map(BaseIdDO::getId).collect(Collectors.toList());
        List<OrderNodeFieldDO> orderFields = orderNodeFieldDao.getByOrderNodeIds(orderNodeIds);
        Map<Long, List<OrderNodeFieldDO>> orderFieldMap = orderFields.stream().collect(Collectors.groupingBy(OrderNodeFieldDO::getBaseOrderNodeId));
        Map<Long, List<OrderNodeResultTypeDO>> orderNodeResultTypeMap = orderNodeResultTypeDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderNodeResultTypeDO::getBaseNodeId));
        Map<Long, List<OrderNodeRouteDO>> orderNodeRouteMap = orderNodeRouteDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderNodeRouteDO::getPrevNodeId));

        List<Long> orderNodeFieldIds = orderFields.stream().map(BaseIdDO::getId).collect(Collectors.toList());
        List<OrderNodeFieldValueDO> orderNodeFieldValueEntities = orderNodeFieldValueDao.getByOrderFieldIds(orderNodeFieldIds);
        Map<Long, OrderNodeFieldValueDO> orderFieldOrderFieldValueMap = orderNodeFieldValueEntities.stream().collect(Collectors.toMap(t -> t.getNodeFieldId(), t -> t));
        ArrayList<OrderNodeAboutResponse> orderNodeList = new ArrayList<>();
        for (OrderNodeDO orderNode : orderNodes) {
            Long nodeId = orderNode.getId();

            List<OrderNodeFieldDO> orderNodeFields = orderFieldMap.get(nodeId);
            List<OrderNodeFieldValueDO> orderNodeFieldValues = new ArrayList<>();
            for (OrderNodeFieldDO orderNodeField : orderNodeFields) {
                Long orderNodeFieldId = orderNodeField.getId();
                orderNodeFieldValues.add(orderFieldOrderFieldValueMap.get(orderNodeFieldId));

            }
            OrderNodeAboutResponse build = OrderNodeAboutResponse.build(orderNode, orderNodeFields, orderNodeResultTypeMap.get(nodeId), orderNodeRouteMap.get(nodeId), orderNodeFieldValues);
            orderNodeList.add(build);
        }
        GetOneOrderResponse build = GetOneOrderResponse.build(byId, orderNodeList);
        return ServiceResult.buildSuccessResult(build);
    }
}
