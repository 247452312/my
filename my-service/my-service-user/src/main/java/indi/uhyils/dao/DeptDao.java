package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DO.DeptMenuDO;
import indi.uhyils.pojo.DO.DeptPowerDO;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.DTO.response.GetDeptsByMenuIdDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

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
     *
     * @return 影响行数
     */
    Integer insertDeptPower(DeptPowerDO middle);

    /**
     * 删除权限集与权限的关联关系
     *
     * @param ids 权限集与权限的关联关系
     *
     * @return影响行数
     */
    Integer deleteDeptPower(List<Long> ids);

    /**
     * 插入权限集与菜单中间表
     *
     * @param t 中间表
     *
     * @return 插入行数
     */
    Integer insertDeptMenu(DeptMenuDO t);

    /**
     * 根据menuId获取权限集
     *
     * @param id menuId
     *
     * @return 权限集
     */
    ArrayList<GetDeptsByMenuIdDTO> getByMenuId(Long id);

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
     *
     * @return 删除条数
     */
    Integer deleteDeptMenuMiddleByDeptId(Long deptId);

    /**
     * 根据权限集id获取所有的带有羁绊的权限
     *
     * @param id 权限集id
     *
     * @return 所有的带有羁绊的权限
     */
    ArrayList<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(Long id);

    /**
     * 删除某权限集的所有权限羁绊
     *
     * @param deptId 权限集id
     *
     * @return 删除数量
     */
    Integer deleteDeptPowerMiddleByDeptId(Long deptId);

    /**
     * 根据权限集id删除角色-权限集关联表
     *
     * @param id 权限集id
     *
     * @return 删除个数
     */
    Integer deleteRoleDeptMiddleByDeptId(Long id);
}
