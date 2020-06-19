package indi.uhyils.dao;

import indi.uhyils.pojo.model.MonitorDO;
import indi.uhyils.pojo.mqinfo.JvmUniqueMark;
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
public interface MonitorDao {

    /**
     * 获根据id获取监控信息
     *
     * @param id 监控信息id
     * @return 监控信息
     */
    MonitorDO getById(String id);

    /**
     * 根据条件不分页查询
     *
     * @param args 条件
     * @return 实例
     */
    ArrayList<MonitorDO> getByArgsNoPage(List<Arg> args);

    /**
     * 根据某些字段获取实例(分页)
     *
     * @param args 字段名称以及内容
     * @param page 分页页码
     * @param size 分页大小
     * @return 实例
     */
    ArrayList<MonitorDO> getByArgs(@Param("args") List<Arg> args, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 插入
     *
     * @param monitorDO 实例
     * @return 插入个数
     */
    int insert(MonitorDO monitorDO);

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
     * 修改结束时间
     *
     * @param id      id
     * @param endTime 要改成的时间
     * @return
     */
    Boolean changeEndTime(@Param("id") String id, @Param("endTime") long endTime);

    /**
     * 根据JVM唯一标识 获取主表主键
     *
     * @param jvmUniqueMark
     * @return
     */
    String getIdByJvmUniqueMark(JvmUniqueMark jvmUniqueMark);
}
