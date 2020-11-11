package indi.uhyils.serviceImpl;

import indi.uhyils.dao.*;
import indi.uhyils.pojo.model.*;
import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.OrderService;
import indi.uhyils.util.OrderBuilder;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时34分
 */
@Service(group = "${spring.profiles.active}")
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderBaseInfoDao orderBaseInfoDao;

    @Resource
    private OrderBaseNodeDao orderBaseNodeDao;

    @Resource
    private OrderBaseNodeFieldDao orderBaseNodeFieldDao;

    @Resource
    private OrderBaseNodeResultTypeDao orderBaseNodeResultTypeDao;

    @Resource
    private OrderBaseNodeRouteDao orderBaseNodeRouteDao;

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

    @Override
    public ServiceResult<String> insertOrder(IdRequest request) {
        //插入order基础信息
        String baseInfoId = request.getId();
        OrderBaseInfoEntity baseInfo = orderBaseInfoDao.getById(baseInfoId);
        OrderInfoEntity orderInfoEntity = OrderBuilder.transBaseInfo2Info(baseInfo);
        orderInfoEntity.preInsert(request);
        orderInfoDao.insert(orderInfoEntity);

        // 获取基础表对应的所有节点
        List<OrderBaseNodeEntity> nodeList = orderBaseNodeDao.getByOrderId(baseInfoId);

        String infoId = orderInfoEntity.getId();
        // 保存所有的路由, 路由比较特殊 需要改node的id
        List<OrderNodeRouteEntity> allRouteEntities = new ArrayList<>();
        // 报存所有的新老node对应关系
        Map<String, String> oldToNew = new HashMap<>(nodeList.size());

        //获取基础节点对应的所有信息(属性,结果类型,路由) 并转换成实例信息插入实例表
        nodeList.forEach(node -> {

            //转换节点本身
            OrderNodeEntity orderNodeEntity = OrderBuilder.transBaseNode2Node(node, infoId);
            orderNodeEntity.preInsert(request);
            orderNodeDao.insert(orderNodeEntity);

            oldToNew.put(node.getId(), orderNodeEntity.getId());
            //获取节点相关的所有东西
            List<OrderBaseNodeFieldEntity> nodeFieldEntity = orderBaseNodeFieldDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeResultTypeEntity> nodeResultTypeEntity = orderBaseNodeResultTypeDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeRouteEntity> routeEntity = orderBaseNodeRouteDao.getByOrderNodeId(node.getId());

            String id = orderNodeEntity.getId();
            //转换节点相关的东西
            nodeFieldEntity.stream().map(field -> OrderBuilder.transBaseField2Field(field, id)).forEach(field -> {
                field.preInsert(request);
                orderNodeFieldDao.insert(field);
            });
            nodeResultTypeEntity.stream().map(resultType -> OrderBuilder.transBaseResultType2ResultType(resultType, id)).forEach(resultType -> {
                resultType.preInsert(request);
                orderNodeResultTypeDao.insert(resultType);
            });
            allRouteEntities.addAll(routeEntity.stream().map(route -> OrderBuilder.transBaseRoute2Route(route, id)).collect(Collectors.toList()));
        });
        allRouteEntities.forEach(t -> {
            t.setPrevNodeId(oldToNew.get(t.getPrevNodeId()));
            t.setNextNodeId(oldToNew.get(t.getNextNodeId()));
            t.preInsert(request);
            orderNodeRouteDao.insert(t);
        });

        return ServiceResult.buildSuccessResult("插入成功", infoId, request);
    }

    @Override
    public ServiceResult<String> insertOrderNode(InsertOrderNodeRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> insertRoute(InsertRouteRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> commitOrder(CommitOrderRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> updateTempOrder(UpdateTempOrderReqeust request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> recallOrder(RecallOrderReqeust request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> frozenOrder(FrozenOrderRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> restartOrder(RestartOrderRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> failOrderNode(FailOrderNodeRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> dealOrderNode(DealOrderNodeRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> approvalOrder(ApprovalOrderRequest request) {
        return null;
    }
}
