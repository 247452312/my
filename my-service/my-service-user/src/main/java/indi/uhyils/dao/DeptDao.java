package indi.uhyils.dao;

import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.DeptPowerMiddle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface DeptDao extends DefaultDao<DeptEntity> {

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
    Integer deleteDeptPower(List<String> ids);

}
