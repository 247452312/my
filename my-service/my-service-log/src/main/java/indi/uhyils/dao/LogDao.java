package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.LogEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface LogDao extends DefaultDao<LogEntity> {


    /**
     * 获取从开始时间到现在的前台请求次数
     *
     * @param time 开始时间
     * @return 从开始时间到现在的前台请求次数
     */
    Integer getCountByStartTime(Long time);
}
