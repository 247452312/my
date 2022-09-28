package indi.uhyils.repository;

import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.CallNode;
import indi.uhyils.pojo.entity.Node;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public interface CallNodeRepository extends BaseEntityRepository<CallNodeDO, CallNode> {

    /**
     * 获取此人有权限的库
     *
     * @param userDTO
     *
     * @return
     */
    List<CallNode> findByUser(UserDTO userDTO);

    /**
     *
     * @param database
     * @param table
     * @return
     */
    Node findNodeByDatabaseAndTable(String database, String table);
}
