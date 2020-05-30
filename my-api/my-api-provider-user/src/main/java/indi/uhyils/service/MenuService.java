package indi.uhyils.service;

import indi.uhyils.pojo.model.MenuEntity;
import indi.uhyils.pojo.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.response.MenuTreeLayuiResponse;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时41分
 */
public interface MenuService extends DefaultEntityService<MenuEntity> {

    ServiceResult<MenuTreeLayuiResponse> getByIFrameAndDepts(GetByIFrameAndDeptsRequest request);

}
