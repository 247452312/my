package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterface extends AbstractDataNode<ProviderInterfaceDO> {


    /**
     * 需要的参数
     */
    private List<ProviderInterfaceParam> shouldParams;


    @Default
    public ProviderInterface(ProviderInterfaceDO data) {
        super(data);
    }

    public ProviderInterface(Long id) {
        super(id, new ProviderInterfaceDO());
    }

    public ProviderInterface(boolean isSysDatabase) {
        super(new ProviderInterfaceDO());
        Asserts.assertTrue(isSysDatabase, "非系统数据库请不要使用此后遭方法");
    }


    @Override
    public void fill(NodeRepository nodeRepository, ProviderInterfaceRepository providerInterfaceRepository) {
        this.shouldParams = providerInterfaceRepository.findParamByInterfaceId(getUnique().orElseThrow(() -> Asserts.makeException("接口未填充")));
    }

    /**
     * 拼装字段信息返回值
     *
     * @return
     */
    public List<FieldInfo> fieldInfo() {
        Optional<ProviderInterfaceDO> providerInterfaceOptional = toData();
        ProviderInterfaceDO providerInterfaceDO = providerInterfaceOptional.orElseThrow(() -> Asserts.makeException("ProviderInterface未填充值"));
        List<FieldInfo> results = new ArrayList<>();

        for (int i = 0; i < shouldParams.size(); i++) {
            ProviderInterfaceParam shouldParam = shouldParams.get(i);
            FieldInfo fieldInfo = new FieldInfo(providerInterfaceDO.getDatabase(), providerInterfaceDO.getTable(), providerInterfaceDO.getTable(), shouldParam.name(), shouldParam.name(), 0, i, shouldParam.type(), (short) 0, (byte) 0);
            results.add(fieldInfo);
        }
        return results;
    }

    @Override
    public NodeInvokeResult getResult(Map<String, String> header, Map<String, Object> params) {
        // providerInterface改造为可以使用的
        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
        nodeInvokeResult.setFieldInfos(fieldInfo());
        return nodeInvokeResult;
    }


    @Override
    public String databaseName() {
        final ProviderInterfaceDO providerInterfaceDO = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        return providerInterfaceDO.getDatabase();
    }

    @Override
    public String tableName() {
        final ProviderInterfaceDO providerInterfaceDO = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        return providerInterfaceDO.getTable();
    }
}
