package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.DynamicCodeDO;
import org.apache.ibatis.annotations.Mapper;


/**
* 动态代码主表(DynamicCode)表 mapper
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@Mapper
public interface DynamicCodeDao extends DefaultDao<DynamicCodeDO> {

}
