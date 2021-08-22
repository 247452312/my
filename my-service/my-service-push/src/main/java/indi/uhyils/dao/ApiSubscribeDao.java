package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.ApiSubscribeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ApiSubscribeDao extends DefaultDao<ApiSubscribeDO> {

    /**
     * 根据cron获取订阅信息
     *
     * @param cron
     * @return
     */
    List<ApiSubscribeDO> getByCron(String cron);

    /**
     * 检查指定订阅有没有重复->只检查此用户的此api群
     *
     * @param entity 新订阅
     * @return 重复个数
     */
    int checkSubscribeRepeat(ApiSubscribeDO entity);
}
