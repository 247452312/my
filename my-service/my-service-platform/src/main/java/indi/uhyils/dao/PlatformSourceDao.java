package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.PlatformSourceDO;
import org.apache.ibatis.annotations.Mapper;


/**
* 资源主表(PlatformSource)表 mapper
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper
public interface PlatformSourceDao extends DefaultDao<PlatformSourceDO> {

}