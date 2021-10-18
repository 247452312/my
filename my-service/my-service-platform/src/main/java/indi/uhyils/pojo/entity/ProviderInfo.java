package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.ProviderInfoDO;
import indi.uhyils.repository.ProviderInfoRepository;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.annotation.Default;

/**
 * 服务提供者表(ProviderInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
public class ProviderInfo extends AbstractDoEntity<ProviderInfoDO> {

    @Default
    public ProviderInfo(ProviderInfoDO data) {
        super(data);
    }

    public ProviderInfo(Long id) {
        super(id, new ProviderInfoDO());
    }

    public ProviderInfo(Long id, ProviderInfoRepository rep) {
        super(id, new ProviderInfoDO());
        completion(rep);
    }

    public ProviderInfo(Identifier id) {
        super(id, new ProviderInfoDO());
    }

}
