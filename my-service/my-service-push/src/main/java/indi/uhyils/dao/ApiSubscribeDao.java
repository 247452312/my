package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.ApiSubscribeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ApiSubscribeDao extends DefaultDao<ApiSubscribeEntity> {

    /**
     * 根据cron获取订阅信息
     *
     * @param cron
     * @return
     */
    List<ApiSubscribeEntity> getByCron(String cron);
}
