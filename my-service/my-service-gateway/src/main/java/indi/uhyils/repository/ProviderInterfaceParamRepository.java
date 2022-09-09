package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ProviderInterfaceParamDO;
import indi.uhyils.pojo.entity.ProviderInterfaceParam;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 接口参数表(ProviderInterfaceParam)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public interface ProviderInterfaceParamRepository extends BaseEntityRepository<ProviderInterfaceParamDO, ProviderInterfaceParam> {

    /**
     * 根据接口获取接口参数
     *
     * @param interfaceId
     *
     * @return
     */
    List<ProviderInterfaceParam> findByInterfaceId(Identifier interfaceId);
}
