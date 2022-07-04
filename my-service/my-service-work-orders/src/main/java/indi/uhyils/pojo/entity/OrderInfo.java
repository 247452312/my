package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.assembler.OrderInfoAssembler;
import indi.uhyils.enums.OrderStatusEnum;
import indi.uhyils.enums.PushTypeEnum;
import indi.uhyils.facade.PushFacade;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.IdMapping;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 工单基础信息样例表(OrderInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分13秒
 */
public class OrderInfo extends AbstractDoEntity<OrderInfoDO> {

    private List<OrderNode> nodes;

    @Default
    public OrderInfo(OrderInfoDO data) {
        super(data);
    }

    public OrderInfo(Long id) {
        super(id, new OrderInfoDO());
    }

    public void forceFillNodes(List<OrderNode> nodes) {
        this.nodes = nodes;
    }

    public void saveSelf(OrderInfoRepository rep) {
        toData().orElseThrow(Asserts::throwOptionalException).setId(null);
        this.setUnique(null);
        this.setUnique(rep.save(this));
        // 修改node的OrderId
        changeNodesOrderId();
    }

    public List<OrderNode> nodes() {
        return nodes;
    }

    private void changeNodesOrderId() {
        if (CollectionUtil.isEmpty(nodes)) {
            return;
        }
        for (OrderNode node : nodes) {
            final OrderNodeDO orderNodeDO = node.toData().orElseThrow(Asserts::throwOptionalException);
            orderNodeDO.setBaseInfoId(getUnique().map(Identifier::getId).orElseThrow(Asserts::throwOptionalException));
        }
    }

    public void saveNode(OrderNodeRepository nodeRepository, OrderNodeFieldRepository fieldRepository, OrderNodeRouteRepository routeRepository, OrderNodeResultTypeRepository resultTypeRepository) {
        if (CollectionUtil.isEmpty(nodes)) {
            return;
        }
        Map<Long, Long> idMappings = new HashMap<>(this.nodes.size());
        for (OrderNode node : this.nodes) {
            IdMapping idMapping = node.saveSelf(nodeRepository);
            idMappings.put(idMapping.getOldId(), idMapping.getNewId());
        }

        for (OrderNode node : nodes) {
            node.changeAndSaveFieldNodeId(idMappings, fieldRepository);
            node.changeAndSaveResultTypeAndRoute(idMappings, routeRepository, resultTypeRepository);
        }

    }

    public void compareAndSave(OrderInfoRepository rep, Long monitorUserId) {
        final OrderInfoDO orderInfoDO = toData().orElseThrow(Asserts::throwOptionalException);
        Long selfMonitor = orderInfoDO.getMonitorUserId();
        if (!Objects.equals(selfMonitor, monitorUserId)) {
            orderInfoDO.setMonitorUserId(monitorUserId);
            this.onUpdate();
            rep.save(this);
        }

    }

    public void changeOrderStatus(OrderInfoRepository rep, OrderStatusEnum code) {
        Integer status = data.getStatus();
        OrderStatusEnum statusEnum = OrderStatusEnum.parse(status);
        Asserts.assertTrue(code != statusEnum, MessageFormat.format("工单状态为:{0} 要修改成:{1},状态相同不能更改", statusEnum.getName(), code.getName()));
        data.setStatus(code.getCode());
        onUpdate();
        rep.save(this);
    }

    public void noticeMonitor(OrderInfoAssembler assem, PushFacade pushFacade, PushTypeEnum pushType, OrderStatusEnum targetStatus) {
        OrderInfoDTO orderInfoDTO = assem.toDTO(this);
        Boolean pushResult = pushFacade.pushMsg(orderInfoDTO, data.getMonitorUserId(), targetStatus, pushType);
        Asserts.assertTrue(pushResult, "推送失败");

    }

    /**
     * 修改订单状态
     *
     * @param rep
     * @param status
     * @param lastStatus
     */
    public void contrastAndChangeOrderStatus(OrderInfoRepository rep, OrderStatusEnum status, OrderStatusEnum lastStatus) {
        // 订单现有状态
        OrderStatusEnum nowStatus = OrderStatusEnum.parse(data.getStatus());
        Asserts.assertTrue(status == nowStatus, MessageFormat.format("工单状态为:{0} 设定状态为:{1},状态需要相同", nowStatus.getName(), status.getName()));
        data.setStatus(lastStatus.getCode());
        onUpdate();
        rep.save(this);
    }
}
