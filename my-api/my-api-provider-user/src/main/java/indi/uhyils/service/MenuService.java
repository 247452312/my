package indi.uhyils.service;

import indi.uhyils.pojo.model.MenuEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.response.IndexMenuTreeLayuiResponse;
import indi.uhyils.pojo.response.MenuHtmlTreeLayuiResponse;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.pojo.response.info.LayuiMenuMenuInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时41分
 */
public interface MenuService extends DefaultEntityService<MenuEntity> {
    /**
     * 获取主页的菜单
     *
     * @param request 请求
     * @return
     */
    ServiceResult<IndexMenuTreeLayuiResponse> getIndexMenu(DefaultRequest request);


    /**
     * 获取菜单tree,并将格式转为前台的格式(menu.html用)
     *
     * @param request 请求
     * @return 格式处理好菜单
     */
    ServiceResult<ArrayList<MenuHtmlTreeLayuiResponse>> getMenuTree(GetByIFrameAndDeptsRequest request);


}
