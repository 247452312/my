package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.ApiEntity;
import indi.uhyils.pojo.model.MsgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface MsgDao extends DefaultDao<MsgEntity> {

}
