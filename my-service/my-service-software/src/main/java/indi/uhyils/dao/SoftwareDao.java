package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.SoftwareDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 中间件表(Software)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分20秒
 */
@Mapper
public interface SoftwareDao extends DefaultDao<SoftwareDO> {


}
