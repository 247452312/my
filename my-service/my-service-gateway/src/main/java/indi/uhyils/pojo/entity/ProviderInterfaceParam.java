package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ProviderInterfaceParamDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 接口参数表(ProviderInterfaceParam)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class ProviderInterfaceParam extends AbstractDoEntity<ProviderInterfaceParamDO> {

    @Default
    public ProviderInterfaceParam(ProviderInterfaceParamDO data) {
        super(data);
    }

    public ProviderInterfaceParam(Long id) {
        super(id, new ProviderInterfaceParamDO());
    }

}
