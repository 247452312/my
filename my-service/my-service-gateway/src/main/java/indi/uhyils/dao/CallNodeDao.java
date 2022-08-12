package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.CallNodeDO;
import org.apache.ibatis.annotations.Mapper;


/**
* 调用节点表, 真正调用的节点(CallNode)表 mapper
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@Mapper
public interface CallNodeDao extends DefaultDao<CallNodeDO> {

}
