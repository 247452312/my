package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.Map;

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

    /**
     * 可执行的实例
     */
    private ProviderExample example;

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
        ProviderInterfaceDO providerInterfaceDO = toDataAndValidate();
        Identifier id = Identifier.build(providerInterfaceDO.getId());
        this.shouldParams = providerInterfaceRepository.findParamByInterfaceId(id);
        InvokeTypeEnum type = InvokeTypeEnum.getByCode(providerInterfaceDO.getInvokeType());
        this.example = providerInterfaceRepository.findExample(id, type);
    }


    @Override
    public NodeInvokeResult getResult(Map<String, String> header, Map<String, Object> params) {
        Asserts.assertTrue(example != null, "执行前请先填充,对应方法 fill");
        return example.invoke(header, params, shouldParams);
    }


    @Override
    public String databaseName() {
        ProviderInterfaceDO providerInterfaceDO = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        return providerInterfaceDO.getDatabase();
    }

    @Override
    public String tableName() {
        ProviderInterfaceDO providerInterfaceDO = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        return providerInterfaceDO.getTable();
    }

}
