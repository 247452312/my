package indi.uhyils.dao.base;

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
public interface DefaultDao<T extends Serializable> extends BaseDao {


    /**
     * 获取一个例子,所有表id不可重复,所以只取一个
     *
     * @param id id
     *
     * @return 实例
     */
    T getById(Long id);

    /**
     * 获取一个例子,所有表id不可重复,所以只取一个
     *
     * @param ids id
     *
     * @return 实例
     */
    List<T> getByIds(@Param("ids") List<Long> ids);

    /**
     * 根据某些字段获取实例(不分页)
     *
     * @param args  条件
     * @param order 排序
     *
     * @return 实例
     */
    ArrayList<T> getByArgsNoPage(@Param("args") List<Arg> args, @Param("order") Order order);

    /**
     * 根据某些字段获取实例(分页)
     *
     * @param args  字段名称以及内容
     * @param order 排序
     * @param limit 分页
     *
     * @return 实例
     */
    ArrayList<T> getByArgs(@Param("args") List<Arg> args, @Param("order") Order order, @Param("limit") Limit limit);

    /**
     * 插入,这里的实例必须是执行过priseInsert方法的实例
     *
     * @param dO 执行过priseInsert方法的实例
     *
     * @return 插入个数
     */
    int insert(T dO);

    /**
     * 更新,这里的实例必须是执行过priseUpdate的实例
     *
     * @param dO 执行过priseUpdate方法的实例,并且含有id
     *
     * @return 更新个数
     */
    int update(T dO);

    /**
     * 更新,这里的实例必须是执行过priseUpdate的实例
     *
     * @param dO   执行过priseUpdate方法的实例,并且含有id
     * @param args 参数
     *
     * @return 更新个数
     */
    int updateByOrder(@Param("data") T dO, @Param("args") List<Arg> args);

    /**
     * 批量更新,这里的实例必须是执行过priseUpdate的实例
     *
     * @param dO 执行过priseUpdate方法的实例,并且含有id
     *
     * @return 更新个数
     */
    int updateBatch(@Param("list") List<T> dO);

    /**
     * 根据条件查询个数
     *
     * @param args 条件
     *
     * @return 个数
     */
    int countByArgs(@Param("args") List<Arg> args);


    /**
     * 获取全表大小
     *
     * @return 全表大小
     */
    int count();

    /**
     * 查询重复
     *
     * @param columnName 字段名称
     * @param value      数值
     *
     * @return 重复个数
     */
    int checkRepeat(@Param("columnName") String columnName, @Param("value") Object value);


}
