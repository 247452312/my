package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.CallNodeAssembler;
import indi.uhyils.dao.CallNodeDao;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.DTO.CallNodeDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.CallNode;
import indi.uhyils.pojo.entity.Node;
import indi.uhyils.repository.CallNodeRepository;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 调用节点表, 真正调用的节点(CallNode)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class CallNodeRepositoryImpl extends AbstractRepository<CallNode, CallNodeDO, CallNodeDao, CallNodeDTO, CallNodeAssembler> implements CallNodeRepository {

    @Autowired
    private NodeRepository nodeRepository;

    protected CallNodeRepositoryImpl(CallNodeAssembler convert, CallNodeDao dao) {
        super(convert, dao);
    }

    @Override
    public List<CallNode> findByUser(UserDTO userDTO) {
        final LambdaQueryWrapper<CallNodeDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CallNodeDO::getCompanyId, userDTO.getId());
        final List<CallNodeDO> callNodeDOS = dao.selectList(queryWrapper);
        return assembler.listToEntity(callNodeDOS);
    }

    @Override
    public Node findNodeByDatabaseAndTable(String database, String table) {
        final LambdaQueryWrapper<CallNodeDO> queryWrapper = Wrappers.lambdaQuery();
        Asserts.assertTrue(StringUtil.isNotEmpty(database), "数据库名称不能为空");
        Asserts.assertTrue(StringUtil.isNotEmpty(table), "表名称不能为空");

        final String val = database + "/" + table;
        queryWrapper.eq(CallNodeDO::getUrl, val);
        final CallNodeDO dO = dao.selectOne(queryWrapper);
        final Long nodeId = dO.getNodeId();
        Asserts.assertTrue(nodeId != null, val + ",没有查询到对应的节点");
        return nodeRepository.find(new CallNode(nodeId));
    }
}
