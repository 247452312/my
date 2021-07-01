package indi.uhyils.handler.impl.init;

import indi.uhyils.enum_.ApiCodeEnum;
import indi.uhyils.handler.InitApiHandler;
import indi.uhyils.pojo.dto.ApiDealDto;
import indi.uhyils.pojo.model.OrderNodeEntity;
import indi.uhyils.pojo.temp.InitApiRequestTemporary;
import indi.uhyils.pojo.temp.InitToRunApiTemporary;
import org.springframework.stereotype.Service;

/**
 * 处理购买自动工单初始化
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月24日 07时49分
 */
@Service
public class InitPurchasingHandlerImpl implements InitApiHandler {


    @Override
    public InitToRunApiTemporary init(InitApiRequestTemporary requestTemporary) {
        // 此节点
        OrderNodeEntity orderNode = requestTemporary.getOrderNode();
        // 上一个节点
        OrderNodeEntity pervOrderNode = requestTemporary.getPervOrderNode();

        ApiDealDto apiDealDto = doInit(orderNode, pervOrderNode);
        return InitToRunApiTemporary.build(orderNode, pervOrderNode, apiDealDto);
    }

    private ApiDealDto doInit(OrderNodeEntity orderNode, OrderNodeEntity pervOrderNode) {
        return ApiDealDto.build(ApiCodeEnum.SUCCESS);
    }
}
