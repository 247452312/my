package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.ParamDO;
import org.apache.ibatis.annotations.Mapper;


/**
* 系统参数表(Param)表 mapper
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年06月17日 15时53分
*/
@Mapper
public interface ParamDao extends DefaultDao<ParamDO> {

}
