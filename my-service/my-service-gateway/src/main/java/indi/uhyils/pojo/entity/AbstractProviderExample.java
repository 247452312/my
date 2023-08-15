package indi.uhyils.pojo.entity;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月08日 15时06分
 */
public abstract class AbstractProviderExample<T extends BaseDO> extends AbstractDoEntity<T> implements ProviderExample {

    /**
     * 对应主表信息
     */
    protected ProviderInterface providerInterface;

    protected AbstractProviderExample(T t) {
        super(t);
    }

    protected AbstractProviderExample() {
    }

    protected AbstractProviderExample(Identifier id, T t) {
        super(id, t);
    }

    protected AbstractProviderExample(Long id, T t) {
        super(id, t);
    }

    @Override
    public NodeInvokeResult invoke(Map<String, String> header, Map<String, Object> params, List<ProviderInterfaceParam> shouldParams) {
        // providerInterface改造为可以使用的
        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
        nodeInvokeResult.setFieldInfos(new ArrayList<>());
        nodeInvokeResult.setResult(new ArrayList<>());
        return nodeInvokeResult;
    }

    @Override
    public void fillInterface(ProviderInterface providerInterface) {
        this.providerInterface = providerInterface;
    }


}