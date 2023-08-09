package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.ProviderInterfaceMysqlDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import java.util.List;
import java.util.Map;

/**
 * mysql接口子表(ProviderInterfaceMysql)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceMysql extends AbstractProviderExample<ProviderInterfaceMysqlDO> implements ProviderExample {

    @Default
    public ProviderInterfaceMysql(ProviderInterfaceMysqlDO data) {
        super(data);
    }

    public ProviderInterfaceMysql(Long id) {
        super(id, new ProviderInterfaceMysqlDO());
    }

}
