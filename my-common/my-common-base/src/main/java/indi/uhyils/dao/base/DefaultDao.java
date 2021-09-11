package indi.uhyils.dao.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 默认的dao,如果dao对应的entity对应一个数据库的话,就需要继承这个类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 15时22分
 */
public interface DefaultDao<T extends Serializable> extends BaseDao, BaseMapper<T> {

    /**
     * 批量更新,这里的实例必须是执行过priseUpdate的实例
     *
     * @param dO 执行过priseUpdate方法的实例,并且含有id
     *
     * @return 更新个数
     */
    int updateBatch(@Param("list") List<T> dO);
}
