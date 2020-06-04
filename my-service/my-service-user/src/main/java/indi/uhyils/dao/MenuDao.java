package indi.uhyils.dao;

import indi.uhyils.pojo.model.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface MenuDao extends DefaultDao<MenuEntity> {


    /**
     * 根据IFrame获取实例
     *
     * @param iframe IFrame
     * @return 实例
     */
    List<MenuEntity> getByIFrame(Integer iframe);

    /**
     * 根据权限集ids获取menuId
     *
     * @param deptIds 权限集ids
     * @return menuIds
     */
    List<String> getByDeptIds(List<String> deptIds);
}
