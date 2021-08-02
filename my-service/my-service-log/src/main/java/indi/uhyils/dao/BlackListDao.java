package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.BlackListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * (BlackList)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 06时40分49秒
 */
@Mapper
public interface BlackListDao extends DefaultDao<BlackListEntity> {


    /**
     * 获取指定ip用户访问的前size个时间
     *
     * @param ip   ip
     * @param size limit
     *
     * @return
     */
    List<Long> getTimeByIp(@Param("ip") String ip, @Param("size") Integer size);

    /**
     * 获取所有的ip黑名单
     *
     * @return ip黑名单
     */
    ArrayList<String> getAllIpBlackList();

}
