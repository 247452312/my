package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.DeptMenuMiddle;
import indi.uhyils.pojo.DO.DeptPowerMiddle;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkResponse;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface DeptDao extends DefaultDao<DeptDO> {

    /**
     * 插入权限集与权限的关联关系
     *
     * @param middle 权限集与权限的关联关系
     * @return 影响行数
     */
    Integer insertDeptPower(DeptPowerMiddle middle);

    /**
     * 删除权限集与权限的关联关系
     *
     * @param ids 权限集与权限的关联关系
     * @return影响行数
     */
    Integer deleteDeptPower(List<Long> ids);

    /**
     * 插入权限集与菜单中间表
     *
     * @param t 中间表
     * @return 插入行数
     */
    Integer insertDeptMenu(DeptMenuMiddle t);

    /**
     * 根据menuId获取权限集
     *
     * @param id menuId
     * @return 权限集
     */
    ArrayList<GetDeptsByMenuIdResponse> getByMenuId(Long id);

    /**
     * 获取全部
     *
     * @return 全部权限集
     */
    ArrayList<DeptDO> getAll();

    /**
     * 根据权限集id删除所有权限集-菜单中间表
     *
     * @param deptId 权限集id
     * @return 删除条数
     */
    Integer deleteDeptMenuMiddleByDeptId(Long deptId);

    /**
     * 根据权限集id获取所有的带有羁绊的权限
     *
     * @param id 权限集id
     * @return 所有的带有羁绊的权限
     */
    ArrayList<GetAllPowerWithHaveMarkResponse> getAllPowerWithHaveMark(Long id);

    /**
     * 删除某权限集的所有权限羁绊
     *
     * @param deptId 权限集id
     * @return 删除数量
     */
    Integer deleteDeptPowerMiddleByDeptId(Long deptId);

    /**
     * 根据权限集id删除角色-权限集关联表
     *
     * @param id 权限集id
     * @return 删除个数
     */
    Integer deleteRoleDeptMiddleByDeptId(Long id);
}
