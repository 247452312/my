package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.MenuDO;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface MenuDao extends DefaultDao<MenuDO> {


    /**
     * 根据IFrame获取实例
     *
     * @param iframe IFrame
     *
     * @return 实例
     */
    List<MenuDO> getByIFrame(Integer iframe);

    /**
     * 根据权限集ids获取menuId
     *
     * @param deptIds 权限集ids
     *
     * @return menuIds
     */
    List<MenuDO> getByDeptIds(List<Long> deptIds);

    /**
     * 根据ids删除菜单
     *
     * @param collect 菜单ids
     *
     * @return 删除个数
     */
    Integer deleteByIds(List<Long> collect);

    /**
     * 根据菜单ids删除权限集与菜单中间表数据
     *
     * @param menuIds 菜单ids
     *
     * @return 删除个数
     */
    Integer deleteDeptMenuByMenuIds(List<Long> menuIds);


    /**
     * 根据权限集ids删除权限集与菜单中间表数据
     *
     * @param deptIds 权限集ids
     *
     * @return 删除个数
     */
    Integer deleteDeptMenuByDeptIds(List<Long> deptIds);

    /**
     * 获取所有叶子菜单(包含羁绊标记)
     *
     * @param deptId 权限集id
     *
     * @return 所有叶子菜单(包含羁绊标记)
     */
    ArrayList<GetAllMenuWithHaveMarkDTO> getAllMenuWithHaveMark(Long deptId);
}
