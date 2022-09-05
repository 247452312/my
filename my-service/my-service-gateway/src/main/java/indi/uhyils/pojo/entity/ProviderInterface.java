package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.DO.ProviderInterfaceParamDO;
import indi.uhyils.pojo.dto.FieldInfoDTO;
import indi.uhyils.repository.ProviderInterfaceParamRepository;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class ProviderInterface extends AbstractDataNode<ProviderInterfaceDO> {


    /**
     * 需要的参数
     */
    private List<ProviderInterfaceParam> sholdParams;


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

    /**
     * 填充参数
     *
     * @param providerInterfaceParamRepository
     */
    public void fillParams(ProviderInterfaceParamRepository providerInterfaceParamRepository) {
        this.sholdParams = providerInterfaceParamRepository.findByInterfaceId(getUnique().orElseThrow(() -> Asserts.makeException("接口未填充")));
    }

    /**
     * 拼装字段信息返回值
     *
     * @return
     */
    public List<FieldInfoDTO> fieldInfo() {
        final Optional<ProviderInterfaceDO> providerInterfaceOptional = toData();
        final ProviderInterfaceDO providerInterfaceDO = providerInterfaceOptional.orElseThrow(() -> Asserts.makeException("ProviderInterface未填充值"));
        return this.sholdParams.stream().map(t -> {
            final ProviderInterfaceParamDO providerInterfaceParamDO = t.toData().get();
            final FieldInfoDTO fieldInfoDTO = new FieldInfoDTO();
            fieldInfoDTO.setDatabase(providerInterfaceDO.getDatabase());
            fieldInfoDTO.setRealTable(providerInterfaceDO.getName());
            fieldInfoDTO.setDecimals(0);
            fieldInfoDTO.setName(providerInterfaceParamDO.getName());
            return fieldInfoDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public NodeInvokeResult getResult() {
        return null;
    }

    @Override
    public String databaseName() {
        final ProviderInterfaceDO providerInterfaceDO = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        return providerInterfaceDO.getDatabase();
    }

    @Override
    public String tableName() {
        final ProviderInterfaceDO providerInterfaceDO = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        return providerInterfaceDO.getName();
    }
}
