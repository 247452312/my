package indi.uhyils.dao;

import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.RoleEntity;
import indi.uhyils.pojo.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface UserDao extends DefaultDao<UserEntity> {

    /**
     * 根据角色Id获取用户的角色
     *
     * @param roleId 角色id
     * @return 角色实例
     */
    RoleEntity getUserRoleById(String roleId);

    /**
     * 根据角色id获取用户的权限集
     *
     * @param roleId 角色id
     * @return 用户的权限集
     */
    List<DeptEntity> getUserDeptsByRoleId(String roleId);


    /**
     * 根据权限集id获取权限
     *
     * @param deptId 权限集id
     * @return 用户的权限
     */
    List<PowerEntity> getUserPowerByDeptId(String deptId);
}
