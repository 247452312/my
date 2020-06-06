package indi.uhyils.dao;

import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.DeptMenuMiddle;
import indi.uhyils.pojo.model.DeptPowerMiddle;
import indi.uhyils.pojo.response.GetDeptsByMenuIdResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
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
    ArrayList<GetDeptsByMenuIdResponse> getByMenuId(String id);

    /**
     * 获取全部
     *
     * @return 全部权限集
     */
    ArrayList<DeptEntity> getAll();

}
