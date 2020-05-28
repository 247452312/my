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


    List<MenuEntity> getByIFrame(Integer data);

    List<String> getByDeptIds(List<String> deptIds);
}
