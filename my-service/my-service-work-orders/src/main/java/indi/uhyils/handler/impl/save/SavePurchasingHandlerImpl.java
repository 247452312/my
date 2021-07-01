package indi.uhyils.handler.impl.save;

import indi.uhyils.enum_.ApiCodeEnum;
import indi.uhyils.handler.SaveApiHandler;
import indi.uhyils.pojo.dto.ApiDealDto;
import indi.uhyils.pojo.model.OrderNodeEntity;
import indi.uhyils.pojo.temp.RunToSaveApiTemporary;
import indi.uhyils.pojo.temp.SaveToTransApiTemporary;
import org.springframework.stereotype.Service;

/**
 * 处理购买自动工单保存
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月24日 07时49分
 */
@Service
public class SavePurchasingHandlerImpl implements SaveApiHandler {
    @Override
    public SaveToTransApiTemporary save(RunToSaveApiTemporary requestTemporary) {
        OrderNodeEntity orderNode = requestTemporary.getOrderNode();
        OrderNodeEntity pervOrderNode = requestTemporary.getPervOrderNode();
        ApiDealDto doRunDto = requestTemporary.getApiDealDto();
        ApiDealDto doSaveDto = doSave(orderNode, pervOrderNode, doRunDto);
        return SaveToTransApiTemporary.build(orderNode, pervOrderNode, doSaveDto);
    }

    private ApiDealDto doSave(OrderNodeEntity orderNode, OrderNodeEntity pervOrderNode, ApiDealDto doSaveDto) {
        return ApiDealDto.build(ApiCodeEnum.SUCCESS);
    }
}
