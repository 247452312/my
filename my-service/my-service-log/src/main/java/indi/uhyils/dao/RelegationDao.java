package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.RelegationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接口降级策略(Relegation)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分22秒
 */
@Mapper
public interface RelegationDao extends DefaultDao<RelegationDO> {


}
