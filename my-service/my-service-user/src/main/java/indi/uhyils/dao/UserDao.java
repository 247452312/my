package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.DeptDO;
import indi.uhyils.pojo.model.PowerDO;
import indi.uhyils.pojo.model.RoleDO;
import indi.uhyils.pojo.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface UserDao extends DefaultDao<UserDO> {

    /**
     * 根据角色Id获取用户的角色
     *
     * @param roleId 角色id
     * @return 角色实例
     */
    RoleDO getUserRoleById(Long roleId);

    /**
     * 根据角色id获取用户的权限集
     *
     * @param roleId 角色id
     * @return 用户的权限集
     */
    ArrayList<DeptDO> getUserDeptsByRoleId(Long roleId);


    /**
     * 根据权限集id获取权限
     *
     * @param deptId 权限集id
     * @return 用户的权限
     */
    List<PowerDO> getUserPowerByDeptId(Long deptId);

    /**
     * 获取全部用户
     *
     * @return 全部用户
     */
    ArrayList<UserDO> getAll();

    /**
     * 对比密码是否和数据库中的相同
     *
     * @param id          用户id
     * @param oldPassword 旧密码
     * @return 是否正确
     */
    Integer checkUserPassword(@Param("id") Long id, @Param("password") String oldPassword);

    /**
     * 获取用户名称
     *
     * @param id id
     * @return 用户名称
     */
    String getNameById(Long id);
}
