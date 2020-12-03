package indi.uhyils.service;

import indi.uhyils.pojo.model.OrderBaseInfoEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public interface OrderBaseInfoService extends DefaultEntityService<OrderBaseInfoEntity> {

    /**
     * 获取全部的基础工单(id与名称)
     *
     * @param request
     * @return
     */
    ServiceResult<ArrayList<OrderBaseInfoEntity>> getAllBaseOrderIdAndName(DefaultRequest request);

}
