package indi.uhyils.dao.base;

import indi.uhyils.pojo.request.model.Arg;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 15时22分
 */
public interface DefaultDao<T extends Serializable> extends BaseDao {


    /**
     * 获取一个例子,所有表id不可重复,所以只取一个
     *
     * @param id id
     * @return 实例
     */
    T getById(String id);

    /**
     * 根据某些字段获取实例(不分页)
     *
     * @param map 条件
     * @return 实例
     */
    ArrayList<T> getByArgsNoPage(List<Arg> map);

    /**
     * 根据某些字段获取实例(分页)
     *
     * @param args 字段名称以及内容
     * @param page 分页页码
     * @param size 分页大小
     * @return 实例
     */
    ArrayList<T> getByArgs(@Param("args") List<Arg> args, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 插入,这里的实例必须是执行过priseInsert方法的实例
     *
     * @param t 执行过priseInsert方法的实例
     * @return 插入个数
     */
    int insert(T t);

    /**
     * 更新,这里的实例必须是执行过priseUpdate的实例
     *
     * @param t 执行过priseUpdate方法的实例,并且含有id
     * @return 更新个数
     */
    int update(T t);

    /**
     * 根据条件查询个数
     *
     * @param args 条件
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
     * @return 重复个数
     */
    int checkRepeat(@Param("columnName") String columnName, @Param("value") Object value);


}
