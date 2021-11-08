package indi.uhyils.repository;

import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.pojo.entity.ProviderInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 数据库连接表(DbInfo)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
public interface DbInfoRepository extends BaseEntityRepository<DbInfoDO, DbInfo> {


    /**
     * 根据生产者获取数据库信息
     *
     * @param provider
     *
     * @return
     */
    List<DbInfo> findByProvider(ProviderInfo provider);

    /**
     * 根据生产者获取数据库信息
     *
     * @param providerId
     *
     * @return
     */
    List<DbInfo> findByProviderId(Identifier providerId);

    /**
     * 根据接口获取数据库连接
     *
     * @param interfaceInfo
     *
     * @return
     */
    DbInfo findByInterfaceInfo(InterfaceInfo interfaceInfo);
}
