package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.InstructionsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Instructions)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分50秒
 */
@Mapper
public interface InstructionsDao extends DefaultDao<InstructionsDO> {


}
