package indi.uhyils.serviceImpl;

import indi.uhyils.dao.*;
import indi.uhyils.pojo.model.*;
import indi.uhyils.pojo.model.base.BaseIdEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.order.GetOneBaseOrderResponse;
import indi.uhyils.pojo.response.order.OrderBaseNodeAboutResponse;
import indi.uhyils.service.OrderBaseInfoService;
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
public class OrderBaseInfoServiceImpl extends BaseDefaultServiceImpl<OrderBaseInfoEntity> implements OrderBaseInfoService {

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


    public OrderBaseInfoDao getDao() {
        return dao;
    }

    public void setDao(OrderBaseInfoDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<ArrayList<OrderBaseInfoEntity>> getAllBaseOrderIdAndName(DefaultRequest request) {
        ArrayList<OrderBaseInfoEntity> result = dao.getAllBaseOrderIdAndName();
        return ServiceResult.buildSuccessResult(result, request);
    }

    @Override
    public ServiceResult<GetOneBaseOrderResponse> getOneOrder(IdRequest request) {
        Long id = request.getId();
        OrderBaseInfoEntity byId = dao.getById(id);
        List<OrderBaseNodeEntity> orderBaseNodes = orderBaseNodeDao.getNoHiddenByOrderId(id);
        List<Long> orderNodeIds = orderBaseNodes.stream().map(BaseIdEntity::getId).collect(Collectors.toList());
        Map<Long, List<OrderBaseNodeFieldEntity>> orderBaseFieldMap = orderBaseNodeFieldDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderBaseNodeFieldEntity::getBaseOrderId));
        Map<Long, List<OrderBaseNodeResultTypeEntity>> orderBaseNodeResultTypeMap = orderBaseNodeResultTypeDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderBaseNodeResultTypeEntity::getBaseNodeId));
        Map<Long, List<OrderBaseNodeRouteEntity>> orderBaseNodeRouteMap = orderBaseNodeRouteDao.getByOrderNodeIds(orderNodeIds).stream().collect(Collectors.groupingBy(OrderBaseNodeRouteEntity::getPrevNodeId));
        ArrayList<OrderBaseNodeAboutResponse> orderBaseNodeList = new ArrayList<>();
        for (OrderBaseNodeEntity orderBaseNode : orderBaseNodes) {
            Long nodeId = orderBaseNode.getId();
            OrderBaseNodeAboutResponse build = OrderBaseNodeAboutResponse.build(orderBaseNode, orderBaseFieldMap.get(nodeId), orderBaseNodeResultTypeMap.get(nodeId), orderBaseNodeRouteMap.get(nodeId));
            orderBaseNodeList.add(build);
        }
        GetOneBaseOrderResponse build = GetOneBaseOrderResponse.build(byId, orderBaseNodeList);
        return ServiceResult.buildSuccessResult(build, request);
    }
}
