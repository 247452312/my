package indi.uhyils.plan;

import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        Map<Long, NodeInvokeResult> planMap = new HashMap<>();
        if (params != null && params.size() != 0) {
            List<Map<String, Object>> value = new ArrayList<>();
            value.add(params);
            planMap.put(-1L, PlanUtil.paramsToResult(value));
        }

        NodeInvokeResult lastResult = null;
        // 补全并执行
        for (MysqlPlan mysqlPlan : plan) {
            mysqlPlan.complete(planMap);
            NodeInvokeResult invoke = mysqlPlan.invoke();
            lastResult = invoke;
            planMap.put(mysqlPlan.getId(), invoke);
        }
        // todo 结果未返回
        return lastResult;
    }


    /**
     * 入参转成结果
     *
     * @return
     */
    public static NodeInvokeResult paramsToResult(List<Map<String, Object>> params) {
        if (CollectionUtil.isEmpty(params)) {
            return new NodeInvokeResult();
        }
        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult();

        final LinkedList<FieldInfo> fieldInfos = new LinkedList<>();

        final List<String> fields = params.stream().flatMap(t -> t.keySet().stream()).distinct().collect(Collectors.toList());
        final Map<String, Object> firstParam = params.get(0);
        first:
        for (int i = 0; i < fields.size(); i++) {
            final String field = fields.get(i);
            if (firstParam.containsKey(field)) {
                final FieldInfo fieldInfo = makeFieldInfo(firstParam.get(i), i, field);
                fieldInfos.add(fieldInfo);
            } else {
                for (Map<String, Object> param : params) {
                    if (param.containsKey(field)) {
                        final FieldInfo fieldInfo = makeFieldInfo(firstParam.get(i), i, field);
                        fieldInfos.add(fieldInfo);
                        continue first;
                    }
                }
                Asserts.makeException("未找到指定类的类型:{}", field);
            }
        }
        nodeInvokeResult.setFieldInfos(fieldInfos);
        nodeInvokeResult.setResult(params);
        return nodeInvokeResult;
    }


    /**
     * 获取字段类型
     *
     * @param i
     * @param field
     *
     * @return
     */
    private static FieldInfo makeFieldInfo(Object o, int i, String field) {
        if (o instanceof Number) {
            return new FieldInfo(null, null, null, field, field, 0, i, FieldTypeEnum.FIELD_TYPE_FLOAT, (short) 0, (byte) 0);
        } else if (o instanceof String) {
            return new FieldInfo(null, null, null, field, field, 0, i, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0);
        } else {
            throw Asserts.makeException("未知的字段类型:{}", o.getClass().getName());
        }
    }

}
