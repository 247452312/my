package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ProviderInterfaceHttpDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * http接口子表(ProviderInterfaceHttp)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceHttp extends AbstractDoEntity<ProviderInterfaceHttpDO> {

    @Default
    public ProviderInterfaceHttp(ProviderInterfaceHttpDO data) {
        super(data);
    }

    public ProviderInterfaceHttp(Long id) {
        super(id, new ProviderInterfaceHttpDO());
    }

}
