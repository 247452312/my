package indi.uhyils.service;


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
import java.util.List;

/**
 * 菜单(Menu)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分48秒
 */
public interface MenuService extends BaseDoService<MenuDTO> {

    /**
     * 将许多权限集添加到一个菜单
     *
     * @param request 将许多权限集添加到一个菜单的请求
     *
     * @return 是否成功
     */
    Boolean putDeptsToMenu(PutDeptsToMenuCommand request);

    /**
     * 获取index
     *
     * @param request
     *
     * @return
     */
    IndexMenuTreeDTO getIndexMenu(DefaultCQE request);

    /**
     * 获取菜单tree,并将格式转为前台的格式(menu.html用)
     *
     * @param request 请求
     *
     * @return 格式处理好菜单
     */
    MenuHtmlTreeDTO getMenuTree(GetByIFrameAndDeptsQuery request);

    /**
     * 获取所有叶子菜单(包含羁绊标记(传入的部门所有))
     *
     * @param request 权限集id
     *
     * @return 所有叶子菜单(包含羁绊标记)
     */
    List<GetAllMenuWithHaveMarkDTO> getAllMenuWithHaveMark(IdQuery request);

    /**
     * 1.删除对应id节点以及所有子节点
     * 2.删除权限集表与菜单连接表中的对应关系
     *
     * @param req 要删除的节点id
     *
     * @return 是否删除成功
     */
    Boolean removeMenu(IdCommand req);

    /**
     * 根据菜单id获取属于这个菜单的权限集以及全部权限集
     *
     * @param request 包含菜单id的请求
     *
     * @return 权限集们
     */
    List<GetDeptsByMenuIdDTO> getDeptsByMenuId(IdQuery request);
}
