package indi.uhyils.facade.impl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.annotation.Facade;
import indi.uhyils.content.OrderContent;
import indi.uhyils.enum_.OrderStatusEnum;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.facade.PushFacade;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.pojo.temp.InitApiRequestTemporary;
import indi.uhyils.protocol.rpc.PushMsgProvider;
import indi.uhyils.rpc.annotation.RpcReference;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月04日 13时09分
 */
@Facade
public class PushFacadeImpl implements PushFacade {

    @RpcReference
    private PushMsgProvider pushMsgProvider;

    @Override
    public Boolean pushMsg(OrderInfoDTO orderInfo, Long userId, OrderStatusEnum targetStatus, PushTypeEnum pushType) {
        PushMsgToSomeoneRequest request = new PushMsgToSomeoneRequest();
        request.setUserId(userId);
        request.setType(pushType.getCode());
        request.setTitle(targetStatus.getTitle());
        request.setMsg(targetStatus.getMsg(orderInfo));
        ServiceResult<Boolean> booleanServiceResult = pushMsgProvider.pushMsgToSomeone(request);
        return booleanServiceResult.validationAndGet();
    }

    @Override
    public void noticeAutoNodeDeal(OrderNode orderNode, OrderNode pervOrder) {
        InitApiRequestTemporary msg = new InitApiRequestTemporary();
        msg.setOrderNode(orderNode.toData());
        msg.setPervOrderNode(pervOrder.toData());
        MqUtil.sendMsg(OrderContent.ORDER_EXCHANGE, OrderContent.ORDER_AUTO_NODE_SEND_QUEUE, JSON.toJSONString(msg));
    }


}
