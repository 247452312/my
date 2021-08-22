package indi.uhyils.handler.impl.run;

import indi.uhyils.enum_.ApiCodeEnum;
import indi.uhyils.handler.RunApiHandler;
import indi.uhyils.pojo.dto.ApiDealDto;
import indi.uhyils.pojo.model.OrderNodeDO;
import indi.uhyils.pojo.temp.InitToRunApiTemporary;
import indi.uhyils.pojo.temp.RunToSaveApiTemporary;
import org.springframework.stereotype.Service;

/**
 * 处理购买自动工单执行逻辑
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月24日 07时49分
 */
@Service
public class RunPurchasingHandlerImpl implements RunApiHandler {
    @Override
    public RunToSaveApiTemporary run(InitToRunApiTemporary requestTemporary) {
        OrderNodeDO orderNode = requestTemporary.getOrderNode();
        OrderNodeDO pervOrderNode = requestTemporary.getPervOrderNode();
        ApiDealDto doInitDto = requestTemporary.getApiDealDto();
        ApiDealDto doRunDto = doRun(orderNode, pervOrderNode, doInitDto);
        return RunToSaveApiTemporary.build(orderNode, pervOrderNode, doRunDto);
    }

    private ApiDealDto doRun(OrderNodeDO orderNode, OrderNodeDO pervOrderNode, ApiDealDto apiDealDto) {
        return ApiDealDto.build(ApiCodeEnum.SUCCESS);
    }
}
