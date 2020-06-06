package indi.uhyils.dao;

import indi.uhyils.pojo.model.PowerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface PowerDao extends DefaultDao<PowerEntity> {

    /**
     * 获取所有表权限
     *
     * @return 所有表权限
     */
    ArrayList<PowerEntity> getAll();

}
