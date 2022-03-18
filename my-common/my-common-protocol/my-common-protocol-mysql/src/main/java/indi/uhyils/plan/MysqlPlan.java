package indi.uhyils.plan;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.MysqlHandlerObserver;
import indi.uhyils.pojo.entity.FieldInfo;
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
     * 根据参数获取结果
     *
     * @param param 之前的结果集 执行计划index, 对应结果 注:执行计划index为-1则是实际入参
     *
     * @return
     */
    JSONArray invoke(Map<Long, JSONArray> param) throws Exception;

    /**
     * 该执行计划在执行计划组中的id
     *
     * @return
     */
    Long index();

    /**
     * 获取结果列信息
     *
     * @return
     */
    List<FieldInfo> colInfos();
}
