package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class ProviderInterface extends AbstractDoEntity<ProviderInterfaceDO> {

    @Default
    public ProviderInterface(ProviderInterfaceDO data) {
        super(data);
    }

    public ProviderInterface(Long id) {
        super(id, new ProviderInterfaceDO());
    }

}
