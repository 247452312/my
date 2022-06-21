package indi.uhyils.repository;

import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DTO.response.GetAllDeptWithHaveMarkDTO;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 角色仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时46分
 */
public interface RoleRepository extends BaseEntityRepository<RoleDO, Role> {

    /**
     * 情况指定角色与部门的联系
     *
     * @param roleId
     */
    void cleanDeptLink(Role roleId);

    /**
     * 添加角色和部门的连接
     *
     * @param roleId
     * @param deptIds
     */
    void addRoleDeptLink(Role roleId, List<Dept> deptIds);

    /**
     * 根据id删除角色和link的连接
     *
     * @param ids
     */
    void removeRoleDeptLink(List<Long> ids);

    /**
     * 获取全部角色
     *
     * @return
     */
    List<Role> getAll();

    /**
     * 获取角色的全部部门
     *
     * @param roleId
     *
     * @return
     */
    List<Role> findRoleDeptLinkByRoleId(Role roleId);

    /**
     * 根据角色id获取
     *
     * @param roleId
     *
     * @return
     */
    List<GetAllDeptWithHaveMarkDTO> findDeptWithHaveMark(Role roleId);
}
