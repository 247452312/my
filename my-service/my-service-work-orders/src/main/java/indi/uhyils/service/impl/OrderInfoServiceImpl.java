package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderInfoAssembler;
import indi.uhyils.assembler.OrderNodeFieldValueAssembler;
import indi.uhyils.assembler.OrderNodeResultTypeAssembler;
import indi.uhyils.assembler.OrderNodeRouteAssembler;
import indi.uhyils.enum_.OrderNodeStatusEnum;
import indi.uhyils.enum_.OrderStatusEnum;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.facade.PushFacade;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DTO.InitOrderDTO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.DTO.OrderNodeFieldValueDTO;
import indi.uhyils.pojo.IdMapping;
import indi.uhyils.pojo.cqe.command.CommitOrderCommand;
import indi.uhyils.pojo.cqe.command.FrozenOrderCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RecallOrderCommand;
import indi.uhyils.pojo.cqe.command.RestartOrderCommand;
import indi.uhyils.pojo.cqe.event.AgreeRecallOrderEvent;
import indi.uhyils.pojo.cqe.event.ApprovalOrderEvent;
import indi.uhyils.pojo.cqe.query.GetAllOrderQuery;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.OrderApply;
import indi.uhyils.pojo.entity.OrderInfo;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.pojo.entity.OrderNodeFieldValue;
import indi.uhyils.pojo.entity.OrderNodeList;
import indi.uhyils.pojo.entity.OrderNodeResultType;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OrderApplyRepository;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeFieldValueRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.service.OrderBaseInfoService;
import indi.uhyils.service.OrderInfoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工单基础信息样例表(OrderInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分14秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_info"})
public class OrderInfoServiceImpl extends AbstractDoService<OrderInfoDO, OrderInfo, OrderInfoDTO, OrderInfoRepository, OrderInfoAssembler> implements OrderInfoService {


    @Autowired
    private OrderBaseInfoService baseInfoService;

    @Autowired
    private OrderNodeRepository nodeRepository;

    @Autowired
    private OrderNodeFieldRepository fieldRepository;

    @Autowired
    private OrderNodeRouteRepository routeRepository;

    @Autowired
    private OrderNodeResultTypeRepository resultTypeRepository;

    @Autowired
    private OrderNodeFieldValueRepository fieldValueRepository;

    @Autowired
    private OrderNodeFieldValueAssembler fieldValueAssembler;

    @Autowired
    private PushFacade pushFacade;

    @Autowired
    private OrderApplyRepository orderApplyRepository;

    @Autowired
    private OrderNodeRepository orderNodeRepository;

    @Autowired
    private OrderNodeResultTypeAssembler typeAssembler;

    @Autowired
    private OrderNodeRouteAssembler routeAssembler;

    public OrderInfoServiceImpl(OrderInfoAssembler assembler, OrderInfoRepository repository) {
        super(assembler, repository);
    }

    @Override
    public InitOrderDTO initOrder(IdCommand request) {
        OrderBaseInfoDTO order = baseInfoService.getOneOrder(new IdQuery(request.getId()));
        OrderInfoDTO infoDTO = assem.baseInfoDTOToInfoDTO(order);
        OrderInfo orderInfo = assem.toEntity(infoDTO);
        // 保存自己,会改变id
        orderInfo.saveSelf(rep);
        // 将修改后的id赋值到node上并保存node
        orderInfo.saveNode(nodeRepository, fieldRepository, routeRepository, resultTypeRepository);
        return assem.toInitOrderDTO(orderInfo);
    }

    @Override
    public List<OrderInfoDTO> getAllOrder(GetAllOrderQuery request) {
        List<OrderInfo> result = rep.findByType(request.getType());
        return assem.listEntityToDTO(result);
    }

    @Override
    public Boolean commitOrder(CommitOrderCommand request) {

        /*添加首节点的真实值*/
        Map<Long, String> value = request.getValue();
        List<OrderNodeFieldValueDTO> orderNodeFieldValueDTOS = fieldValueAssembler.valueToValueDTO(value);
        List<OrderNodeFieldValue> orderNodeFieldValues = fieldValueAssembler.listDTOToEntity(orderNodeFieldValueDTOS);
        fieldValueRepository.save(orderNodeFieldValues);

        /*更新主表的监管人*/
        OrderInfo orderInfo = new OrderInfo(request.getOrderId());
        orderInfo.completion(rep);
        orderInfo.compareAndSave(rep, request.getMonitorUserId());

        /*更新节点表的处理人,抄送人*/
        Map<Long, Long> dealUserIds = request.getDealUserIds();
        Map<Long, Long> noticeUserIds = request.getNoticeUserIds();
        List<Long> nodeIds = new ArrayList<>(dealUserIds.size() + noticeUserIds.size());
        nodeIds.addAll(dealUserIds.keySet());
        nodeIds.addAll(noticeUserIds.keySet());
        List<Identifier> identifiers = nodeIds.stream().distinct().map(Identifier::new).collect(Collectors.toList());
        List<OrderNode> orderNodes = nodeRepository.find(identifiers);
        OrderNodeList orderNodeList = new OrderNodeList(orderNodes);
        orderNodeList.compareAndSaveDealUser(dealUserIds);
        orderNodeList.compareAndSaveNoticeUser(noticeUserIds);
        orderNodeList.saveAll(nodeRepository);
        return true;
    }

    @Override
    public Boolean recallOrder(RecallOrderCommand request) {
        /*1.修改工单状态*/
        OrderInfo orderInfo = new OrderInfo(request.getOrderId());
        orderInfo.completion(rep);
        OrderStatusEnum withdrawing = OrderStatusEnum.WITHDRAWING;
        orderInfo.changeOrderStatus(rep, withdrawing);

        /*工单状态修改完成后通知工单监管人,进行审批处理,是否予以撤回,注意,此处返回值为是否发送申请成功*/
        orderInfo.noticeMonitor(assem, pushFacade, PushTypeEnum.EMAIL, withdrawing);
        return true;
    }

    @Override
    public Boolean agreeRecallOrder(AgreeRecallOrderEvent request) {
        /*1.修改工单状态*/
        OrderInfo orderInfo = new OrderInfo(request.getOrderId());
        orderInfo.completion(rep);
        OrderStatusEnum status = OrderStatusEnum.WITHDRAWED;
        orderInfo.changeOrderStatus(rep, status);

        /*工单状态修改完成后通知工单监管人,进行审批或通知处理*/
        orderInfo.noticeMonitor(assem, pushFacade, PushTypeEnum.EMAIL, status);
        return true;
    }

    @Override
    public Boolean frozenOrder(FrozenOrderCommand request) {
        /*1.修改工单状态*/
        OrderInfo orderInfo = new OrderInfo(request.getOrderId());
        orderInfo.completion(rep);
        OrderStatusEnum status = OrderStatusEnum.STOP;
        orderInfo.changeOrderStatus(rep, status);

        /*工单状态修改完成后通知工单监管人,进行审批或通知处理*/
        orderInfo.noticeMonitor(assem, pushFacade, PushTypeEnum.EMAIL, status);
        return true;

    }

    @Override
    public Boolean restartOrder(RestartOrderCommand request) {
        /*1.修改工单状态*/
        OrderInfo orderInfo = new OrderInfo(request.getOrderId());
        orderInfo.completion(rep);
        OrderStatusEnum status = OrderStatusEnum.STOP;
        OrderStatusEnum lastStatus = OrderStatusEnum.START;
        orderInfo.contrastAndChangeOrderStatus(rep, status, lastStatus);

        /*工单状态修改完成后通知工单监管人,进行审批或通知处理*/
        orderInfo.noticeMonitor(assem, pushFacade, PushTypeEnum.EMAIL, lastStatus);
        return true;

    }

    @Override
    public Boolean approvalOrder(ApprovalOrderEvent request) {
        OrderApply orderApply = new OrderApply(request.getOrderApplyId());
        orderApply.completion(orderApplyRepository);
        orderApply.completionThisNode(orderNodeRepository, fieldRepository, routeRepository, resultTypeRepository);
        /*0.将此节点状态置位已转交*/
        orderApply.changeThisNodeStatus(orderNodeRepository, OrderNodeStatusEnum.TRANSFERRED);

        /*1.新增下一节点,复制此节点的参数,状态置为停用*/
        OrderNode lastNode = orderApply.copyToLastNode();

        IdMapping idMapping = lastNode.saveSelf(nodeRepository);
        /*2.将此节点的属性,结果,路由复制到下一个节点上去*/
        // 属性
        lastNode.changeAndSaveFieldNodeId(idMapping, fieldRepository);
        // 结果与路由
        lastNode.changeAndSaveResultTypeAndRoute(idMapping, routeRepository, resultTypeRepository);

        /*3.新增此节点已转交的'结果'并将此节点的'结果'置位此结果*/
        OrderNodeResultType resultByTrans = lastNode.createResultByTrans(typeAssembler);
        resultTypeRepository.save(resultByTrans);
        OrderNode node = orderApply.node();
        node.addTestResult(resultByTrans, nodeRepository);
        /*4.新增此节点到下一节点的路由*/
        node.createRoute(routeRepository, routeAssembler, resultByTrans.getId(), lastNode);

        /*5.将下一节点置位等待开始*/
        lastNode.changeStatus(nodeRepository, OrderNodeStatusEnum.WAIT_STATUS);

        /*6.通知下一节点处理人*/
        orderApply.noticeTargetUser(rep, assem, pushFacade, PushTypeEnum.EMAIL);
        return true;
    }
}
