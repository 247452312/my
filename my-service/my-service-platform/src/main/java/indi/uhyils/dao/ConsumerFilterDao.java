package indi.uhyils.dao;

import indi.uhyils.pojo.DO.ConsumerFilterDO;
import org.apache.ibatis.annotations.Mapper;
import indi.uhyils.dao.base.DefaultDao;

/**
 * 消费过滤表(ConsumerFilter)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分03秒
 */
@Mapper
public interface ConsumerFilterDao extends DefaultDao<ConsumerFilterDO> {


}
