package indi.uhyils.dao;

import indi.uhyils.pojo.model.DictEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface DictDao extends DefaultDao<DictEntity> {


    /**
     * 根据字典code获取字典id
     * @param code code
     * @return
     */
    String getIdByCode(String code);
}
