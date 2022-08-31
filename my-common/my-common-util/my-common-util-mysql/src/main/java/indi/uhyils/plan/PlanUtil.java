package indi.uhyils.plan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 执行计划工具类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 08时38分
 */
public final class PlanUtil {

    private PlanUtil() {
        throw new RuntimeException("工具类不能初始化");
    }


    /**
     * 执行执行计划
     *
     * @return 每个执行计划的结果 key->执行计划id value->执行计划执行结果
     */
    public static NodeInvokeResult execute(List<MysqlPlan> plan, Map<String, Object> params) {
        // 初始化参数
        Map<Long, JSONArray> planMap = new HashMap<>();
        if (params != null && params.size() != 0) {
            JSONArray value = new JSONArray();
            value.add(params);
            planMap.put(-1L, value);
        }

        JSONArray lastResult;
        // 补全并执行
        for (MysqlPlan mysqlPlan : plan) {
            final Map<Long, List<Map<String, Object>>> collect = planMap.entrySet().stream().collect(Collectors.toMap(Entry::getKey, t -> t.getValue().stream().map(s -> JSONObject.parseObject(JSON.toJSONString(s))).map(s -> (Map<String, Object>) s).collect(Collectors.toList())));
            mysqlPlan.complete(collect);
            MysqlPlanResult invoke = mysqlPlan.invoke();
            final JSONArray result = invoke.result();
            lastResult = result;
            planMap.put(mysqlPlan.getId(), result);
        }
        // todo 结果未返回
        return null;
    }

}
