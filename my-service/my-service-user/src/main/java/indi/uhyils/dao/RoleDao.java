package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.RoleDeptMiddle;
import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.response.GetAllDeptWithHaveMarkResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface RoleDao extends DefaultDao<RoleEntity> {


    /**
     * 插入权限集对应权限的关系
     *
     * @param middle 中间表(执行过priseInsert的)
     * @return 受影响的行数
     */
    Integer insertRoleDept(RoleDeptMiddle middle);

    /**
     * 删除角色对应权限集的关系
     *
     * @param ids 中间表id
     * @return 删除个数
     */
    Integer deleteRoleDept(List<String> ids);

    /**
     * 获取所有的角色
     *
     * @return 所有角色
     */
    ArrayList<RoleEntity> getAll();

    /**
     * 获取所有权限集以及角色是否有此权限集的标记
     *
     * @param id 角色id
     * @return 所有权限集以及角色是否有此权限集的标记
     */
    ArrayList<GetAllDeptWithHaveMarkResponse> getAllDeptWithHaveMark(String id);

    /**
     * 删除角色对应的所有权限集羁绊
     *
     * @param roleId
     * @return
     */
    Integer deleteRoleDeptMiddleByRoleId(String roleId);


    /**
     * 根据角色id将用户角色置空
     *
     * @param id 角色id
     * @return 更新数量
     */
    Integer updateUserRoleToNullByRoleId(String id);
}
