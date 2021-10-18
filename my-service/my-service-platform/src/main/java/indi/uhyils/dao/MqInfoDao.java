package indi.uhyils.dao;

import indi.uhyils.pojo.DO.MqInfoDO;
import org.apache.ibatis.annotations.Mapper;
import indi.uhyils.dao.base.DefaultDao;

/**
 * mq连接信息表(MqInfo)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Mapper
public interface MqInfoDao extends DefaultDao<MqInfoDO> {


}
