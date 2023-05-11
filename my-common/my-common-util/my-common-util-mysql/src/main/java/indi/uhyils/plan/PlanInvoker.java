package indi.uhyils.plan;

import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
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
 * 执行计划者
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 08时38分
 */
public class PlanInvoker {


    private final List<MysqlPlan> plan;

    public PlanInvoker(List<MysqlPlan> plan) {
        this.plan = plan;
    }

    /**
     * 执行执行计划
     *
     * @return 每个执行计划的结果 key->执行计划id value->执行计划执行结果
     */
    public NodeInvokeResult execute() {
        return execute(new HashMap<>(16));
    }

    /**
     * 执行 执行计划
     *
     * @param params 入参 正常格式 key为字段名称 value为对应字段值
     *
     * @return 每个执行计划的结果
     */
    public NodeInvokeResult execute(Map<String, Object> params) {
        // 初始化参数
        Map<Long, NodeInvokeResult> planParamMap = new HashMap<>();
        if (params != null && params.size() != 0) {
            List<Map<String, Object>> value = new ArrayList<>();
            value.add(params);
            planParamMap.put(-1L, paramsToResult(value));
        }

        NodeInvokeResult lastResult = null;
        // 补全并执行
        for (MysqlPlan mysqlPlan : plan) {
            mysqlPlan.complete(planParamMap);
            NodeInvokeResult invoke = mysqlPlan.invoke();
            lastResult = invoke;
            planParamMap.put(mysqlPlan.getId(), invoke);
        }
        return lastResult;
    }

    /**
     * 由于执行计划的规则为将此执行计划之前的所有执行计划的结果作为入参 所以此处的作用为:
     * <p>
     * 将入参 伪装为 id为-1的执行计划的执行结果来作为执行计划链条的起点入参
     *
     * @return
     */
    private NodeInvokeResult paramsToResult(List<Map<String, Object>> params) {
        if (CollectionUtil.isEmpty(params)) {
            return new NodeInvokeResult();
        }
        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult();

        final LinkedList<FieldInfo> fieldInfos = new LinkedList<>();

        final List<String> fields = params.stream().flatMap(t -> t.keySet().stream()).distinct().collect(Collectors.toList());
        final Map<String, Object> firstParam = params.get(0);
        MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        first:
        for (int i = 0; i < fields.size(); i++) {
            final String field = fields.get(i);
            if (firstParam.containsKey(field)) {
                final FieldInfo fieldInfo = FieldTypeEnum.makeFieldInfo(mysqlTcpInfo.getDatabase(), MysqlContent.DEFAULT_PARAM_TABLE, MysqlContent.DEFAULT_PARAM_TABLE, firstParam.get(i), i, field);
                fieldInfos.add(fieldInfo);
            } else {
                for (Map<String, Object> param : params) {
                    if (param.containsKey(field)) {
                        final FieldInfo fieldInfo = FieldTypeEnum.makeFieldInfo(mysqlTcpInfo.getDatabase(), MysqlContent.DEFAULT_PARAM_TABLE, MysqlContent.DEFAULT_PARAM_TABLE, firstParam.get(i), i, field);
                        fieldInfos.add(fieldInfo);
                        continue first;
                    }
                }
                Asserts.throwException("未找到指定类的类型:{}", field);
            }
        }
        nodeInvokeResult.setFieldInfos(fieldInfos);
        nodeInvokeResult.setResult(params);
        return nodeInvokeResult;
    }


}
