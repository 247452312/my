package indi.uhyils.dao;

import indi.uhyils.pojo.model.RoleDeptMiddle;
import indi.uhyils.pojo.model.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface RoleDao extends DefaultDao<RoleEntity> {

    Boolean insertRoleDept(RoleDeptMiddle middle);

    boolean deleteRoleDept(List<String> ids);
}
