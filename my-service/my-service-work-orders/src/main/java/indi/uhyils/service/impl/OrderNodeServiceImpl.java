package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderNodeAssembler;
import indi.uhyils.builder.OrderApplyBuilder;
import indi.uhyils.enum_.OrderNodeResultTypeEnum;
import indi.uhyils.enum_.OrderNodeRunTypeEnum;
import indi.uhyils.enum_.OrderNodeStatusEnum;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.pojo.DO.OrderApplyDO;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.DealOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.FailOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.IncapacityFailOrderNodeCommand;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.DTO.DealOrderNodeDTO;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.pojo.temp.CheckNodeFieldResultTemporary;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.service.OrderNodeService;
import indi.uhyils.util.RpcApiUtil;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 工单节点样例表(OrderNode)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分21秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_node"})
public class OrderNodeServiceImpl extends AbstractDoService<OrderNodeDO, OrderNode, OrderNodeDTO, OrderNodeRepository, OrderNodeAssembler> implements OrderNodeService {

    public OrderNodeServiceImpl(OrderNodeAssembler assembler, OrderNodeRepository repository) {
        super(assembler, repository);
    }


    @Override
    public Boolean deleteByIds(IdsCommand request) {
        Integer updateDate = new Long(System.currentTimeMillis() / 1000).intValue();
        Long updateUser = request.getUser().getId();

        /*删除属性*/
        boolean deleteFieldByNodeIds = orderBaseNodeFieldDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除结果类型*/
        boolean deleteResultTypeByNodeIds = orderBaseNodeResultTypeDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除路由*/
        boolean deleteRouteByNodeIds = orderBaseNodeRouteDao.deleteByNodeIds(request.getIds(), updateUser, updateDate) != 0;
        /*删除本体*/
        boolean deleteByIds = dao.deleteByIds(request.getIds(), updateUser, updateDate) != 0;

        return ServiceResult.buildSuccessResult("删除执行成功", deleteFieldByNodeIds & deleteResultTypeByNodeIds & deleteRouteByNodeIds & deleteByIds);
    }

    @Override
    public Boolean failOrderNode(FailOrderNodeCommand request) {
        orderNodeDao.makeOrderFault(request.getOrderNodeId(), OrderNodeStatusEnum.FAULT.getCode(), OrderNodeResultTypeEnum.FAULT.getCode(), request.getMsg());
        return ServiceResult.buildSuccessResult("处理成功", true);
    }

    @Override
    public DealOrderNodeDTO dealOrderNode(DealOrderNodeCommand request) throws Exception {
        /*前提:判断节点值是否允许*/
        CheckNodeFieldResultTemporary checkNodeFieldResult = checkNodeAllow(request.getOrderNodeFieldValueMap());
        if (!checkNodeFieldResult.getAllow()) {
            return ServiceResult.buildFailedResult("节点值判断出错", DealOrderNodeDTO.buildCheckFaild(checkNodeFieldResult.getAllow(), checkNodeFieldResult.getDetailResult()));
        }
        /*1.结束当前工单节点(节点状态),处理结果类型->处理成功,处理结果id选择,处理人建议*/
        Long nodeId = request.getNodeId();
        OrderNodeDO orderNode = orderNodeDao.getById(nodeId);
        orderNode.setStatus(OrderNodeStatusEnum.OVER.getCode());
        orderNode.setResultType(OrderNodeResultTypeEnum.SUCCESS.getCode());
        orderNode.setResultId(request.getResultId());
        orderNode.setSuggest(request.getSuggest());
        orderNode.preUpdate();
        orderNodeDao.update(orderNode);
        /*2.填充对应节点所需的属性值,*/
        Map<Long, Object> orderNodeFieldValueMap = request.getOrderNodeFieldValueMap();
        for (Map.Entry<Long, Object> entry : orderNodeFieldValueMap.entrySet()) {
            OrderNodeFieldValueDO t = new OrderNodeFieldValueDO();
            Long key = entry.getKey();
            Object value = entry.getValue();
            t.setNodeFieldId(key);
            t.setRealValue(String.valueOf(value));
            t.preInsert();
            orderNodeFieldValueDao.insert(t);
        }
        /*3.将下一节点置为等待开始(并通知自动处理模块检测是否为自动处理模块)*/
        OrderNodeDO nextNode = orderNodeDao.getNextNodeByNodeAndResult(request.getNodeId(), request.getResultId());
        nextNode.setStatus(OrderNodeStatusEnum.WAIT_STATUS.getCode());
        nextNode.preUpdate();
        orderNodeDao.update(nextNode);
        Integer runType = nextNode.getRunType();
        if (OrderNodeRunTypeEnum.AUTO.getCode().equals(runType)) {
            noticeAutoDealOrder(nextNode.getId(), request.getNodeId());
        }
        return ServiceResult.buildSuccessResult("处理成功", DealOrderNodeDTO.buildSuccess());
    }

    @Override
    public Boolean incapacityFailOrderNode(IncapacityFailOrderNodeCommand request) throws Exception {
        Long orderNodeId = request.getOrderNodeId();
        OrderNodeDO orderNode = orderNodeDao.getById(orderNodeId);
        /*1.将节点状态置为转交中*/
        orderNode.setStatus(OrderNodeStatusEnum.TRANSFER.getCode());
        orderNode.preUpdate();
        orderNodeDao.update(orderNode);
        /*2.插入申请表*/
        OrderInfoDO order = orderInfoDao.getById(orderNode.getBaseInfoId());
        OrderApplyDO orderApply = OrderApplyBuilder.buildTransApplyByOrderNode(orderNode, order.getMonitorUserId());
        orderApply.preInsert();
        orderApplyDao.insert(orderApply);
        /*3.通知审批人*/
        ServiceResult<String> userName = (ServiceResult) RpcApiUtil.rpcApiTool("UserService", "getNameById", IdQuery.build(request, request.getRecommendUserId()));
        PushMsgToSomeoneRequest pushMsgToSomeoneRequest = PushMsgToSomeoneRequest
            .build(request, order.getMonitorUserId(), PushTypeEnum.EMAIL.getCode(), "工单节点转交申请", order.getId() + "工单转交撤回,请尽快审批,转交目标人:" + userName.getData().toString());
        RpcApiUtil.rpcApiTool("PushService", "pushMsgToSomeone", pushMsgToSomeoneRequest);
        return ServiceResult.buildSuccessResult(true);
    }
}
