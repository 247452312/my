package indi.uhyils.dao;

import indi.uhyils.pojo.model.RoleDeptMiddle;
import indi.uhyils.pojo.model.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

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
}
