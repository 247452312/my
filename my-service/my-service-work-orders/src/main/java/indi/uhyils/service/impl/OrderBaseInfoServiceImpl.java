package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderBaseInfoAssembler;
import indi.uhyils.enum_.OrderNodeTypeEnum;
import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.DO.OrderBaseNodeFieldDO;
import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;
import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.InitOrderDTO;
import indi.uhyils.pojo.DTO.GetOneBaseOrderDTO;
import indi.uhyils.pojo.DTO.GetOneOrderDTO;
import indi.uhyils.pojo.DTO.OrderNodeAboutDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.OrderBaseInfo;
import indi.uhyils.repository.OrderBaseInfoRepository;
import indi.uhyils.service.OrderBaseInfoService;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.OrderBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分54秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_base_info"})
public class OrderBaseInfoServiceImpl extends AbstractDoService<OrderBaseInfoDO, OrderBaseInfo, OrderBaseInfoDTO, OrderBaseInfoRepository, OrderBaseInfoAssembler> implements OrderBaseInfoService {

    public OrderBaseInfoServiceImpl(OrderBaseInfoAssembler assembler, OrderBaseInfoRepository repository) {
        super(assembler, repository);
    }


    @Override
    public List<OrderBaseInfoDTO> getAllBaseOrderIdAndName(DefaultCQE request) {
        return dao.getAllBaseOrderIdAndName();
    }

    @Override
    public GetOneBaseOrderDTO getOneOrder(IdQuery request) {
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
        ArrayList<OrderNodeAboutDTO> orderNodeList = new ArrayList<>();
        for (OrderNodeDO orderNode : orderNodes) {
            Long nodeId = orderNode.getId();

            List<OrderNodeFieldDO> orderNodeFields = orderFieldMap.get(nodeId);
            List<OrderNodeFieldValueDO> orderNodeFieldValues = new ArrayList<>();
            for (OrderNodeFieldDO orderNodeField : orderNodeFields) {
                Long orderNodeFieldId = orderNodeField.getId();
                orderNodeFieldValues.add(orderFieldOrderFieldValueMap.get(orderNodeFieldId));

            }
            OrderNodeAboutDTO build = OrderNodeAboutDTO.build(orderNode, orderNodeFields, orderNodeResultTypeMap.get(nodeId), orderNodeRouteMap.get(nodeId), orderNodeFieldValues);
            orderNodeList.add(build);
        }
        GetOneOrderDTO build = GetOneOrderDTO.build(byId, orderNodeList);
        return ServiceResult.buildSuccessResult(build);
    }

    @Override
    public InitOrderDTO initOrder(IdCommand request) {
        //插入order基础信息
        Long baseInfoId = request.getId();
        OrderBaseInfoDO baseInfo = orderBaseInfoDao.getById(baseInfoId);
        OrderInfoDO orderInfoEntity = OrderBuilder.transBaseInfo2Info(baseInfo);
        orderInfoEntity.preInsert(request);
        orderInfoDao.insert(orderInfoEntity);

        // 获取基础表对应的所有节点
        List<OrderBaseNodeDO> nodeList = orderBaseNodeDao.getNoHiddenByOrderId(baseInfoId);

        Long infoId = orderInfoEntity.getId();
        // 保存所有的路由, 路由比较特殊 需要改node的id
        List<OrderNodeRouteDO> allRouteEntities = new ArrayList<>();
        // 报存所有的新老node对应关系
        Map<Long, Long> oldToNew = new HashMap<>(nodeList.size());
        // 创建之后的首节点的属性(返回给前台用)
        ArrayList<OrderNodeFieldDO> orderNodeField = new ArrayList<>();
        // 每个节点的处理人(返回给前台用)
        HashMap<Long, Long> dealUserIds = new HashMap<>(nodeList.size());
        // 每个节点的抄送人(返回给前台用)
        HashMap<Long, Long> noticeUserIds = new HashMap<>(nodeList.size());

        //获取基础节点对应的所有信息(属性,结果类型,路由) 并转换成实例信息插入实例表
        nodeList.forEach(node -> {

            boolean isStartNode = OrderNodeTypeEnum.START.getCode().equals(node.getType());

            //转换节点本身
            OrderNodeDO orderNodeEntity = OrderBuilder.transBaseNode2Node(node, infoId);
            try {
                orderNodeEntity.preInsert(request);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            orderNodeDao.insert(orderNodeEntity);

            // 填充默认处理人与抄送人
            dealUserIds.put(orderNodeEntity.getId(), orderNodeEntity.getRunDealUserId());
            noticeUserIds.put(orderNodeEntity.getId(), orderNodeEntity.getNoticeUserId());

            oldToNew.put(node.getId(), orderNodeEntity.getId());
            //获取节点相关的所有东西(属性,结果类型,路由)
            List<OrderBaseNodeFieldDO> baseNodeFieldEntity = orderBaseNodeFieldDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeResultTypeDO> baseNodeResultTypeEntity = orderBaseNodeResultTypeDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeRouteDO> baseRouteEntity = orderBaseNodeRouteDao.getByOrderNodeId(node.getId());

            Long id = orderNodeEntity.getId();
            baseNodeFieldEntity.stream().map(field -> OrderBuilder.transBaseField2Field(field, id)).forEach(field -> {
                if (isStartNode) {
                    orderNodeField.add(field);
                }
                try {
                    field.preInsert(request);
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }
                orderNodeFieldDao.insert(field);
            });
            baseNodeResultTypeEntity.stream().map(resultType -> OrderBuilder.transBaseResultType2ResultType(resultType, id)).forEach(resultType -> {
                try {
                    resultType.preInsert(request);
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }
                orderNodeResultTypeDao.insert(resultType);
            });
            allRouteEntities.addAll(baseRouteEntity.stream().map(route -> OrderBuilder.transBaseRoute2Route(route, id)).collect(Collectors.toList()));
        });
        allRouteEntities.forEach(t -> {
            t.setPrevNodeId(oldToNew.get(t.getPrevNodeId()));
            t.setNextNodeId(oldToNew.get(t.getNextNodeId()));
            try {
                t.preInsert(request);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            orderNodeRouteDao.insert(t);
        });

        InitOrderDTO build = InitOrderDTO.build(infoId, orderNodeField, baseInfo.getMonitorUserId(), dealUserIds, noticeUserIds);
        return ServiceResult.buildSuccessResult("插入成功", build);
    }
}
