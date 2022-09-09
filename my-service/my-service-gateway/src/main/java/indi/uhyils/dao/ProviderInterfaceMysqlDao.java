package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.ProviderInterfaceMysqlDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * mysql接口子表(ProviderInterfaceMysql)表 mapper
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Mapper
public interface ProviderInterfaceMysqlDao extends DefaultDao<ProviderInterfaceMysqlDO> {

}
