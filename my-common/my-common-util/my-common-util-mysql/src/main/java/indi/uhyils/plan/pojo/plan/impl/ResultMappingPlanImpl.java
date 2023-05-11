package indi.uhyils.plan.pojo.plan.impl;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import indi.uhyils.util.Asserts;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时33分
 */
public class ResultMappingPlanImpl extends ResultMappingPlan {


    public ResultMappingPlanImpl(Map<String, String> headers, MysqlPlan lastMainPlan, List<SQLSelectItem> selectList) {
        super(headers, lastMainPlan, selectList);
    }


    @Override
    public NodeInvokeResult invoke() {
        /**
         * todo 结果映射需要做的事情
         */
        final List<FieldInfo> fieldInfos = this.lastNodeInvokeResult.getFieldInfos();
        final List<Map<String, Object>> lastResult = this.lastNodeInvokeResult.getResult();
        final List<String> needField = selectList.stream().map(t -> t.getExpr().toString()).collect(Collectors.toList());
        // 只允许有一个*
        if (needField.contains("*")) {
            return lastNodeInvokeResult;
        }

        Map<String, FieldInfo> fieldInfoMap = fieldInfos.stream().collect(Collectors.toMap(FieldInfo::getFieldName, t -> t));
        List<FieldInfo> newFieldInfo = needField.stream().map(t -> {
            if (t.startsWith("&")) {
                NodeInvokeResult nodeInvokeResult = lastAllPlanResult.get(Long.parseLong(t.substring(1)));
                List<FieldInfo> lastFieldInfos = nodeInvokeResult.getFieldInfos();
                Asserts.assertTrue(lastFieldInfos != null && lastFieldInfos.size() == 1, "映射时需要有且仅有一个字段来映射");
                return lastFieldInfos.get(0);
            } else {
                return fieldInfoMap.get(t);
            }
        }).collect(Collectors.toList());

        final List<Map<String, Object>> newResultList = lastResult.stream().map(t -> {
            Map<String, Object> newResult = new HashMap<>(selectList.size());
            for (Entry<String, Object> entry : t.entrySet()) {
                if (MysqlUtil.ignoreCaseAndQuotesContains(needField, entry.getKey()) || needField.contains("*")) {
                    newResult.put(entry.getKey(), entry.getValue());
                }
            }
            return newResult;
        }).collect(Collectors.toList());

        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult();
        nodeInvokeResult.setFieldInfos(newFieldInfo);
        nodeInvokeResult.setResult(newResultList);
        return nodeInvokeResult;
    }
}
