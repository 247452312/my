package indi.uhyils.service;

import indi.uhyils.pojo.model.MenuEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.GetByIFrameAndDeptsRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.response.*;

import java.util.ArrayList;

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
     * @return 主页菜单 包括主页信息 logo信息 菜单信息
     */
    ServiceResult<IndexMenuTreeResponse> getIndexMenu(DefaultRequest request);


    /**
     * 获取菜单tree,并将格式转为前台的格式(menu.html用)
     *
     * @param request 请求
     * @return 格式处理好菜单
     */
    ServiceResult<MenuHtmlTreeResponse> getMenuTree(GetByIFrameAndDeptsRequest request);


    /**
     * 1.删除对应id节点以及所有子节点
     * 2.删除权限集表与菜单连接表中的对应关系
     *
     * @param req 要删除的节点id
     * @return 是否删除成功
     */
    ServiceResult<Boolean> deleteMenu(IdRequest req);

    /**
     * 根据菜单id获取属于这个菜单的权限集以及全部权限集
     *
     * @param req 包含菜单id的请求
     * @return 权限集们
     */
    ServiceResult<ArrayList<GetDeptsByMenuIdResponse>> getDeptsByMenuId(IdRequest req);

    /**
     * 获取开始界面快捷入口信息
     *
     * @param request 默认请求
     * @return 开始界面快捷入口信息
     */
    ServiceResult<QuickStartResponse> getQuickStartResponse(DefaultRequest request);


}
