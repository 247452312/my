package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ProviderInterfaceRpcDO;
import indi.uhyils.pojo.entity.ProviderExample;
import indi.uhyils.pojo.entity.ProviderInterfaceRpc;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * http接口子表(ProviderInterfaceRpc)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public interface ProviderInterfaceRpcRepository extends BaseEntityRepository<ProviderInterfaceRpcDO, ProviderInterfaceRpc> {

    /**
     * 根据providerId获取
     *
     * @param id
     *
     * @return
     */
    ProviderExample findByProviderId(Identifier id);
}
