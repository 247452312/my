package indi.uhyils.repository;

import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.entity.CallNode;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public interface CallNodeRepository extends BaseEntityRepository<CallNodeDO, CallNode> {

}