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

    RoleEntity getUserRoleById(String roleId);

    List<DeptEntity> getUserDeptsByRoleId(String roleId);

    List<PowerEntity> getUserPowerByDeptId(String deptId);
}
