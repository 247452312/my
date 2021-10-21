package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ProviderInfoDO;
import indi.uhyils.pojo.entity.ProviderInfo;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 服务提供者表(ProviderInfo)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
public interface ProviderInfoRepository extends BaseEntityRepository<ProviderInfoDO, ProviderInfo> {


    /**
     * 检查名称是否重复
     *
     * @param name
     *
     * @return
     */
    Boolean checkNameRepeat(String name);
}
