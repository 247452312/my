package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.request.GetByIFrameAndDeptsQuery;
import indi.uhyils.pojo.DTO.request.PutDeptsToMenuCommand;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import indi.uhyils.pojo.DTO.response.IndexMenuTreeDTO;
import indi.uhyils.pojo.DTO.response.MenuHtmlTreeDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * 菜单接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月28日 12时41分
 */
public interface MenuProvider extends DTOProvider<MenuDTO> {

    /**
     * 获取主页的菜单
     *
     * @param request 请求
     *
     * @return 主页菜单 包括主页信息 logo信息 菜单信息
     */
    ServiceResult<IndexMenuTreeDTO> getIndexMenu(DefaultCQE request);


    /**
     * 将许多权限集添加到一个菜单
     *
     * @param request 将许多权限集添加到一个菜单的请求
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuCommand request);

    /**
     * 获取菜单tree,并将格式转为前台的格式(menu.html用)
     *
     * @param request 请求
     *
     * @return 格式处理好菜单
     */
    ServiceResult<MenuHtmlTreeDTO> getMenuTree(GetByIFrameAndDeptsQuery request);


    /**
     * 1.删除对应id节点以及所有子节点
     * 2.删除权限集表与菜单连接表中的对应关系
     *
     * @param req 要删除的节点id
     *
     * @return 是否删除成功
     */
    ServiceResult<Boolean> removeMenu(IdCommand req);

    /**
     * 根据菜单id获取属于这个菜单的权限集以及全部权限集
     *
     * @param req 包含菜单id的请求
     *
     * @return 权限集们
     */
    ServiceResult<List<GetDeptsByMenuIdDTO>> getDeptsByMenuId(IdQuery req);

    /**
     * 获取所有叶子菜单(包含羁绊标记)
     *
     * @param request 权限集id
     *
     * @return 所有叶子菜单(包含羁绊标记)
     */
    ServiceResult<List<GetAllMenuWithHaveMarkDTO>> getAllMenuWithHaveMark(IdQuery request);

}
