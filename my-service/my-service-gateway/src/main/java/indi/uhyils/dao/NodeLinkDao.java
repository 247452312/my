package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.NodeLinkDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 中间节点与外部节点关联关系(NodeLink)表 mapper
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Mapper
public interface NodeLinkDao extends DefaultDao<NodeLinkDO> {

}
