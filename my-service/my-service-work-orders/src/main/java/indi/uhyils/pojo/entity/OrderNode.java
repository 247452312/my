package indi.uhyils.pojo.entity;

import indi.uhyils.assembler.OrderNodeResultTypeAssembler;
import indi.uhyils.assembler.OrderNodeRouteAssembler;
import indi.uhyils.builder.OrderNodeResultTypeBuilder;
import indi.uhyils.builder.OrderNodeRouteBuilder;
import indi.uhyils.enum_.OrderNodeResultTypeEnum;
import indi.uhyils.enum_.OrderNodeRunTypeEnum;
import indi.uhyils.enum_.OrderNodeStatusEnum;
import indi.uhyils.facade.PushFacade;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;
import indi.uhyils.pojo.DTO.OrderNodeRouteDTO;
import indi.uhyils.pojo.IdMapping;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeFieldValueRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.BeanUtil;
import indi.uhyils.util.CollectionUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单节点样例表(OrderNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分20秒
 */
public class OrderNode extends AbstractDoEntity<OrderNodeDO> {

    /**
     * 节点属性
     */
    private List<OrderNodeField> fields;

    /**
     * 节点结果类型
     */
    private List<OrderNodeResultType> resultTypes;

    /**
     * 节点结果路由
     */
    private List<OrderNodeRoute> routes;

    public OrderNode(OrderNodeDO dO) {
        super(dO);
    }

    public OrderNode(Long id) {
        super(id, new OrderNodeDO());
    }

    public void forceFillInfo(List<OrderNodeField> fields, List<OrderNodeResultType> resultTypes, List<OrderNodeRoute> routes) {
        this.fields = fields;
        this.resultTypes = resultTypes;
        this.routes = routes;
    }

    public List<OrderNodeField> fields() {
        return fields;
    }

    /**
     * 保存自身
     *
     * @param nodeRepository 节点
     */
    public IdMapping saveSelf(OrderNodeRepository nodeRepository) {
        Long oldId = this.getUnique().getId();
        this.setUnique(null);
        toDo().setId(null);
        nodeRepository.save(this);
        Long newId = this.getUnique().getId();
        return IdMapping.build(newId, oldId);
    }

    public void changeAndSaveFieldNodeId(Map<Long, Long> idMappings, OrderNodeFieldRepository fieldRepository) {
        if (CollectionUtil.isEmpty(fields)) {
            return;
        }
        for (OrderNodeField field : fields) {
            OrderNodeFieldDO orderNodeFieldDO = field.toDo();
            orderNodeFieldDO.setBaseOrderNodeId(idMappings.get(orderNodeFieldDO.getBaseOrderNodeId()));
            field.setUnique(null);
            orderNodeFieldDO.setId(null);
            field.setUnique(fieldRepository.save(field));
        }
    }

    public void changeAndSaveFieldNodeId(IdMapping idMappings, OrderNodeFieldRepository fieldRepository) {
        Map<Long, Long> trans = new HashMap<>(1);
        trans.put(idMappings.getOldId(), idMappings.getNewId());
        changeAndSaveFieldNodeId(trans, fieldRepository);
    }

    public void changeAndSaveResultTypeAndRoute(Map<Long, Long> idMappings, OrderNodeRouteRepository routeRepository, OrderNodeResultTypeRepository resultTypeRepository) {
        if (CollectionUtil.isEmpty(resultTypes) || CollectionUtil.isEmpty(routes)) {
            return;
        }
        for (OrderNodeResultType resultType : resultTypes) {
            Long oldId = resultType.getUnique().getId();
            OrderNodeResultTypeDO orderNodeResultTypeDO = resultType.toDo();
            orderNodeResultTypeDO.setBaseNodeId(idMappings.get(orderNodeResultTypeDO.getBaseNodeId()));
            orderNodeResultTypeDO.setId(null);
            resultType.setUnique(null);
            resultType.setUnique(resultTypeRepository.save(resultType));
            Long newId = resultType.getUnique().getId();
            idMappings.put(oldId, newId);
        }

        for (OrderNodeRoute route : routes) {
            OrderNodeRouteDO orderNodeRouteDO = route.toDo();
            orderNodeRouteDO.setNextNodeId(idMappings.get(orderNodeRouteDO.getNextNodeId()));
            orderNodeRouteDO.setPrevNodeId(idMappings.get(orderNodeRouteDO.getPrevNodeId()));
            orderNodeRouteDO.setResultId(idMappings.get(orderNodeRouteDO.getResultId()));
            orderNodeRouteDO.setId(null);
            route.setUnique(null);
            route.setUnique(routeRepository.save(route));
        }
    }

    public void changeAndSaveResultTypeAndRoute(IdMapping idMappings, OrderNodeRouteRepository routeRepository, OrderNodeResultTypeRepository resultTypeRepository) {
        Map<Long, Long> trans = new HashMap<>(1);
        trans.put(idMappings.getOldId(), idMappings.getNewId());
        changeAndSaveResultTypeAndRoute(trans, routeRepository, resultTypeRepository);
    }

    public OrderNode copy() {
        OrderNodeDO dO = new OrderNodeDO();
        BeanUtil.copyProperties(data, dO);
        OrderNode orderNode = new OrderNode(dO);
        orderNode.forceFillInfo(fields, resultTypes, routes);
        return orderNode;
    }

    public OrderNodeResultType createResultByTrans(OrderNodeResultTypeAssembler typeAssembler) {
        OrderNodeResultTypeDTO transResult = OrderNodeResultTypeBuilder.build(getUnique().getId(), "转交");
        return typeAssembler.toEntity(transResult);

    }

    public void addTestResult(OrderNodeResultType result, OrderNodeRepository nodeRepository) {
        data.setResultId(result.toDo().getId());
        data.setResultType(OrderNodeResultTypeEnum.TRANSFER.getCode());
        onUpdate();
        nodeRepository.save(this);
    }

    public OrderNodeRoute createRoute(OrderNodeRouteRepository routeRepository, OrderNodeRouteAssembler routeAssembler, Identifier resultId, OrderNode lastNode) {
        OrderNodeRouteDTO build = OrderNodeRouteBuilder.build(getUnique().getId(), resultId.getId(), lastNode.getUnique().getId());
        OrderNodeRoute orderNodeRoute = routeAssembler.toEntity(build);
        routeRepository.save(orderNodeRoute);
        return orderNodeRoute;
    }

    public void assertAllow() {
        OrderNodeStatusEnum statusEnum = OrderNodeStatusEnum.parse(data.getStatus());
        Asserts.assertTrue(statusEnum == OrderNodeStatusEnum.IN_START, "节点处于不能被处理状态:" + statusEnum.getName());

    }

    public void fillField(OrderNodeFieldRepository fieldRepository) {
        if (this.fields == null) {
            this.fields = fieldRepository.findByNodeId(getUnique().getId());
        }


    }

    public void assertAndSaveFieldValues(OrderNodeFieldValueRepository fieldValueRepository, Map<Long, Object> orderNodeFieldValueMap) {
        Asserts.assertTrue(this.fields != null, "节点属性本身信息不存在!");

        for (OrderNodeField field : fields) {
            Long id = field.getUnique().getId();
            Asserts.assertTrue(orderNodeFieldValueMap.containsKey(id), field.toDo().getName() + " 未填写");
            String realValue = String.valueOf(orderNodeFieldValueMap.get(id));
            OrderNodeFieldValue orderNodeFieldValue = new OrderNodeFieldValue(field, realValue);
            orderNodeFieldValue.assertSelf();
            fieldValueRepository.save(orderNodeFieldValue);

        }
    }

    public void successAndClose(OrderNodeRepository rep, Long resultId, String suggest) {
        data.setStatus(OrderNodeStatusEnum.OVER.getCode());
        data.setResultType(OrderNodeResultTypeEnum.SUCCESS.getCode());
        data.setResultId(resultId);
        data.setSuggest(suggest);
        onUpdate();
        rep.save(this);
    }

    public OrderNode nextOrder(OrderNodeRepository rep) {
        Asserts.assertTrue(data.getResultId() != null, "没有结果,不能路由到下一个节点");
        return rep.findNext(this);
    }

    public void start(OrderNodeRepository rep) {
        changeStatus(rep, OrderNodeStatusEnum.WAIT_STATUS);
    }

    public void checkAuto(PushFacade pushFacade, OrderNode pervOrder) {
        Integer runType = data.getRunType();
        if (OrderNodeRunTypeEnum.AUTO == OrderNodeRunTypeEnum.parse(runType)) {
            // 通知自动处理模块
            pushFacade.noticeAutoNodeDeal(this, pervOrder);
        }
    }

    public void transfer(OrderNodeRepository rep) {
        changeStatus(rep, OrderNodeStatusEnum.TRANSFER);
    }

    public void changeStatus(OrderNodeRepository rep, OrderNodeStatusEnum status) {
        data.setStatus(status.getCode());
        onUpdate();
        rep.save(this);
    }

    public OrderInfo findBaseInfo(OrderInfoRepository infoRepository) {
        return infoRepository.find(new Identifier(data.getBaseInfoId()));
    }
}
