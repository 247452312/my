package indi.uhyils.dao.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * 默认的dao,如果dao对应的entity对应一个数据库的话,就需要继承这个类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 15时22分
 */
public interface DefaultDao<T extends Serializable> extends BaseDao, BaseMapper<T> {

}
