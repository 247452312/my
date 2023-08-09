package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ProviderInterfaceMysqlDO;
import indi.uhyils.pojo.entity.ProviderExample;
import indi.uhyils.pojo.entity.ProviderInterfaceMysql;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * mysql接口子表(ProviderInterfaceMysql)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public interface ProviderInterfaceMysqlRepository extends BaseEntityRepository<ProviderInterfaceMysqlDO, ProviderInterfaceMysql> {

    /**
     * 根据providerId获取
     *
     * @param id
     *
     * @return
     */
    ProviderExample findByProviderId(Identifier id);
}
