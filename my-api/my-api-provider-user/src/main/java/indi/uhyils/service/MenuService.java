package indi.uhyils.service;

import indi.uhyils.pojo.model.MenuEntity;
import indi.uhyils.pojo.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.response.MenuTreeLayuiResponse;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * 菜单接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时41分
 */
public interface MenuService extends DefaultEntityService<MenuEntity> {

    /**
     * 根据iframe和权限获取菜单
     *
     * @param request
     * @return
     */
    ServiceResult<MenuTreeLayuiResponse> getByIFrameAndDepts(GetByIFrameAndDeptsRequest request);

}
