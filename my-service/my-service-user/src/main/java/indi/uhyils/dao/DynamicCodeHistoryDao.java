package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.DynamicCodeHistoryDO;
import org.apache.ibatis.annotations.Mapper;


/**
* 动态代码历史记录表(DynamicCodeHistory)表 mapper
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@Mapper
public interface DynamicCodeHistoryDao extends DefaultDao<DynamicCodeHistoryDO> {

}
