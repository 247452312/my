package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 mapper
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Mapper
public interface ProviderInterfaceDao extends DefaultDao<ProviderInterfaceDO> {

}
