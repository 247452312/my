package indi.uhyils.serviceImpl;

import indi.uhyils.dao.*;
import indi.uhyils.pojo.model.*;
import indi.uhyils.pojo.model.base.BaseIdEntity;
import indi.uhyils.pojo.request.GetAllOrderRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.order.GetOneOrderResponse;
import indi.uhyils.pojo.response.order.OrderNodeAboutResponse;
import indi.uhyils.service.OrderInfoService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Service(group = "${spring.profiles.active}")
public class OrderInfoServiceImpl extends BaseDefaultServiceImpl<OrderInfoEntity> implements OrderInfoService {

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
    public ServiceResult<ArrayList<OrderInfoEntity>> getAllOrder(GetAllOrderRequest request) {
        ArrayList<OrderInfoEntity> result = dao.getOrderByType(request.getType());
        return ServiceResult.buildSuccessResult(result, request);
    }

    @Override
    public ServiceResult<GetOneOrderResponse> getOneOrder(IdRequest request) {
        Long id = request.getId();
        OrderInfoEntity byId = dao.getById(id);
        List<OrderNodeEntity> orderNodes = orderNodeDao.getByOrderId(id);
        List<Long> orderNodeIds = orderNodes.stream().map(BaseIdEntity::getId).collect(Collectors.toList());
        List<OrderNodeFieldEntity> orderFields = orderNodeFieldDao.getByOrderNodeIds(orderNodeIds);
        Map<Long, List<OrderNodeFieldEntity>> orderFieldMap = orderFields.stream().collect(Collectors.groupingBy(OrderNodeFieldEntity::getBaseOrderNodeId));
        Map<Long, List<OrderNodeResultTypeEntity>> orderNodeResultTypeMap = orderNodeResultTypeDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderNodeResultTypeEntity::getBaseNodeId));
        Map<Long, List<OrderNodeRouteEntity>> orderNodeRouteMap = orderNodeRouteDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderNodeRouteEntity::getPrevNodeId));

        List<Long> orderNodeFieldIds = orderFields.stream().map(BaseIdEntity::getId).collect(Collectors.toList());
        List<OrderNodeFieldValueEntity> orderNodeFieldValueEntities = orderNodeFieldValueDao.getByOrderFieldIds(orderNodeFieldIds);
        Map<Long, OrderNodeFieldValueEntity> orderFieldOrderFieldValueMap = orderNodeFieldValueEntities.stream().collect(Collectors.toMap(t -> t.getNodeFieldId(), t -> t));
        ArrayList<OrderNodeAboutResponse> orderNodeList = new ArrayList<>();
        for (OrderNodeEntity orderNode : orderNodes) {
            Long nodeId = orderNode.getId();

            List<OrderNodeFieldEntity> orderNodeFields = orderFieldMap.get(nodeId);
            List<OrderNodeFieldValueEntity> orderNodeFieldValues = new ArrayList<>();
            for (OrderNodeFieldEntity orderNodeField : orderNodeFields) {
                Long orderNodeFieldId = orderNodeField.getId();
                orderNodeFieldValues.add(orderFieldOrderFieldValueMap.get(orderNodeFieldId));

            }
            OrderNodeAboutResponse build = OrderNodeAboutResponse.build(orderNode, orderNodeFields, orderNodeResultTypeMap.get(nodeId), orderNodeRouteMap.get(nodeId), orderNodeFieldValues);
            orderNodeList.add(build);
        }
        GetOneOrderResponse build = GetOneOrderResponse.build(byId, orderNodeList);
        return ServiceResult.buildSuccessResult(build, request);
    }
}
