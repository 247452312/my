package indi.uhyils.dao;

import indi.uhyils.pojo.model.MonitorJvmStatusDetailDO;
import indi.uhyils.pojo.request.model.Arg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时55分
 */
@Mapper
public interface MonitorJvmStatusDetailDao {

    /**
     * 获根据id获取监控信息
     *
     * @param id 监控信息id
     * @return 监控信息
     */
    MonitorJvmStatusDetailDO getById(String id);

    /**
     * 根据条件不分页查询
     *
     * @param args 条件
     * @return 实例
     */
    ArrayList<MonitorJvmStatusDetailDO> getByArgsNoPage(List<Arg> args);

    /**
     * 根据某些字段获取实例(分页)
     *
     * @param args 字段名称以及内容
     * @param page 分页页码
     * @param size 分页大小
     * @return 实例
     */
    ArrayList<MonitorJvmStatusDetailDO> getByArgs(@Param("args") List<Arg> args, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 插入
     *
     * @param monitorDO 实例
     * @return 插入个数
     */
    int insert(MonitorJvmStatusDetailDO monitorDO);

    /**
     * 获取全表大小
     *
     * @return 全表大小
     */
    Integer count();


    /**
     * 根据条件查询个数
     *
     * @param args 条件
     * @return 个数
     */
    Integer countByArgs(@Param("args") List<Arg> args);

    /**
     * 查询重复
     *
     * @param columnName 字段名称
     * @param value      数值
     * @return 重复个数
     */
    int checkRepeat(@Param("columnName") String columnName, @Param("value") Object value);

    /**
     * 根据主表id获取分表数据
     *
     * @param id 主表id
     * @return 主表id对应的分表数据
     */
    List<MonitorJvmStatusDetailDO> getByMonitorId(String id);
}
