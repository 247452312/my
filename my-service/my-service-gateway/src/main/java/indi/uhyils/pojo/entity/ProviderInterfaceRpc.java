package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.ProviderInterfaceRpcDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import java.util.List;
import java.util.Map;

/**
 * http接口子表(ProviderInterfaceRpc)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceRpc extends AbstractProviderExample<ProviderInterfaceRpcDO> implements ProviderExample {

    @Default
    public ProviderInterfaceRpc(ProviderInterfaceRpcDO data) {
        super(data);
    }

    public ProviderInterfaceRpc(Long id) {
        super(id, new ProviderInterfaceRpcDO());
    }

}
