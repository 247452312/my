package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.entity.ProviderInterface;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public interface ProviderInterfaceRepository extends BaseEntityRepository<ProviderInterfaceDO, ProviderInterface> {

    /**
     * 获取唯一接口信息
     *
     * @param invokeType
     * @param path
     *
     * @return
     */
    ProviderInterface find(Integer invokeType, String path);
}
