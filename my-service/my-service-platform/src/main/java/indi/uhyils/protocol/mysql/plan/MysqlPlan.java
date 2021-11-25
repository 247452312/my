package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.protocol.mysql.pojo.MysqlHandlerObserver;
import indi.uhyils.protocol.mysql.pojo.entity.FieldInfo;
import java.util.List;
import java.util.Map;

/**
 * mysql执行计划
 * ps: 入参中可以以 ${planIndex.paramName}来引入之前执行计划的结果
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月18日 11时31分
 */
public interface MysqlPlan extends MysqlHandlerObserver {

    /**
     * 这个执行计划指向的表名
     *
     * @return
     */
    SQLTableSource table();

    /**
     * 这个执行计划的入参
     *
     * @return
     */
    List<String> param();

    /**
     * 要查询的字段列表
     *
     * @return
     */
    List<String> selectList();

    /**
     * 该执行计划在执行计划组中的id
     *
     * @return
     */
    Long index();

    /**
     * 查询条件
     *
     * @return
     */
    List<MysqlWhere> wheres();

    /**
     * 获取结果列信息
     *
     * @return
     */
    List<FieldInfo> colInfos();

    /**
     * 根据参数获取结果
     *
     * @param param 之前的结果集 执行计划index, 对应结果
     *
     * @return
     */
    JSONArray invoke(Map<Long, JSONArray> param);

    /**
     * 根据提前指定的参数获取结果
     *
     * @return
     */
    JSONArray invoke();

}
