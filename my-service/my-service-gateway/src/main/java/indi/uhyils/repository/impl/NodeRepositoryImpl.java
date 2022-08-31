package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.NodeAssembler;
import indi.uhyils.assembler.ProviderInterfaceAssembler;
import indi.uhyils.dao.NodeDao;
import indi.uhyils.dao.ProviderInterfaceDao;
import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.DTO.NodeDTO;
import indi.uhyils.pojo.entity.AbstractDataNode;
import indi.uhyils.pojo.entity.Node;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.repository.base.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 转换节点表(Node)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Repository
public class NodeRepositoryImpl extends AbstractRepository<Node, NodeDO, NodeDao, NodeDTO, NodeAssembler> implements NodeRepository {

    @Autowired
    private ProviderInterfaceRepository providerInterfaceRepository;


    protected NodeRepositoryImpl(NodeAssembler convert, NodeDao dao) {
        super(convert, dao);
    }


    @Override
    public AbstractDataNode find(String path) {
        final LambdaQueryWrapper<NodeDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(NodeDO::getUrl, path);
        final NodeDO nodeDO = dao.selectOne(queryWrapper);
        if (nodeDO != null) {
            return assembler.toEntity(nodeDO);
        }
        return providerInterfaceRepository.find(path);
    }
}
