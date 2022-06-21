package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderInfoAssembler;
import indi.uhyils.assembler.OrderNodeAssembler;
import indi.uhyils.enums.OrderStatusEnum;
import indi.uhyils.enums.PushTypeEnum;
import indi.uhyils.facade.PushFacade;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DTO.DealOrderNodeDTO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.cqe.command.DealOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.FailOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.command.IncapacityFailOrderNodeCommand;
import indi.uhyils.pojo.entity.OrderApply;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.pojo.entity.OrderNodeList;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OrderApplyRepository;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeFieldValueRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.service.OrderNodeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OrderNodeFieldRepository fieldRepository;

    @Autowired
    private OrderNodeRouteRepository routeRepository;

    @Autowired
    private OrderNodeResultTypeRepository resultTypeRepository;

    @Autowired
    private OrderNodeFieldValueRepository fieldValueRepository;

    @Autowired
    private PushFacade pushFacade;

    @Autowired
    private OrderInfoRepository infoRepository;

    @Autowired
    private OrderApplyRepository applyRepository;

    @Autowired
    private OrderInfoAssembler orderInfoAssembler;

    public OrderNodeServiceImpl(OrderNodeAssembler assembler, OrderNodeRepository repository) {
        super(assembler, repository);
    }

    @Override
    public Boolean deleteByIds(IdsCommand request) {
        List<Identifier> nodeIds = request.getIds().stream().map(Identifier::new).collect(Collectors.toList());
        List<OrderNode> orderNodes = rep.find(nodeIds);
        OrderNodeList orderNodeList = new OrderNodeList(orderNodes);
        /*删除属性*/
        orderNodeList.delFields(fieldRepository);
        /*删除路由*/
        orderNodeList.delRoutes(routeRepository);
        /*删除结果类型*/
        orderNodeList.delResultType(resultTypeRepository);
        /*删除本体*/
        orderNodeList.delSelf(rep);
        return true;
    }

    @Override
    public Boolean failOrderNode(FailOrderNodeCommand request) {
        rep.makeOrderFault(request.getOrderNodeId(), request.getMsg());
        return true;
    }

    @Override
    public DealOrderNodeDTO dealOrderNode(DealOrderNodeCommand request) {
        /*前提:判断节点值是否允许*/
        OrderNode orderNode = new OrderNode(request.getNodeId());
        orderNode.completion(rep);
        // 检验自身是否允许被修改
        orderNode.assertAllow();
        // 检验传入的值是否正确
        orderNode.fillField(fieldRepository);

        /*1.结束当前工单节点(节点状态),处理结果类型->处理成功,处理结果id选择,处理人建议*/
        orderNode.successAndClose(rep, request.getResultId(), request.getSuggest());

        orderNode.assertAndSaveFieldValues(fieldValueRepository, request.getOrderNodeFieldValueMap());

        /*2.将下一节点置为等待开始(并通知自动处理模块检测是否为自动处理模块)*/
        OrderNode next = orderNode.nextOrder(rep);
        next.start(rep);
        next.checkAuto(pushFacade, orderNode);
        return DealOrderNodeDTO.buildSuccess();
    }

    @Override
    public Boolean incapacityFailOrderNode(IncapacityFailOrderNodeCommand request) {
        OrderNode orderNode = new OrderNode(request.getOrderNodeId());
        orderNode.completion(rep);
        /*1.将节点状态置为转交中*/
        orderNode.transfer(rep);
        /*2.插入申请表*/
        OrderApply apply = assem.toApply(orderNode, orderNode.findBaseInfo(infoRepository));
        apply.saveSelf(applyRepository);
        /*3.通知审批人*/
        apply.noticeTargetUser(infoRepository, orderInfoAssembler, OrderStatusEnum.CIRCULATION, pushFacade, PushTypeEnum.EMAIL);
        return true;
    }
}
