package indi.uhyils.pojo.entity;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.pojo.entity.DataNode;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import java.util.Map;

/**
 * 数据处理节点模板
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月30日 09时12分
 */
public abstract class AbstractDataNode<T extends BaseDO> extends AbstractDoEntity<T> implements DataNode {


    public AbstractDataNode(T t) {
        super(t);
    }

    public AbstractDataNode(Long id, T t) {
        super(id, t);
    }

    @Override
    public NodeInvokeResult getResult(Map<String, String> header, Map<String, Object> params) {
        return new NodeInvokeResult(null);
    }

    /**
     * 填充必要数据
     *
     * @param nodeRepository
     * @param providerInterfaceRepository
     */
    public abstract void fill(NodeRepository nodeRepository, ProviderInterfaceRepository providerInterfaceRepository);

}
