package indi.uhyils.plan.pojo.plan.impl;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.google.common.base.Objects;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.mysql.util.StringUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
    public void complete(Map<Long, NodeInvokeResult> planArgs) {
        // 填充占位符
        completePlaceholder(planArgs);
        this.lastAllPlanResult = planArgs;
        List<Long> planIds = planArgs.keySet().stream().sorted(Long::compareTo).collect(Collectors.toList());
        for (int i = planIds.size() - 1; i >= 0; i--) {
            Long key = planIds.get(i);
            NodeInvokeResult nodeInvokeResult = planArgs.get(key);

        }

    }

    @Override
    public NodeInvokeResult invoke() {
        /*1.如果结果列只有一个* 则直接返回 (如果除了*还有其他的 则报错)*/
        final List<FieldInfo> lastFieldInfos = this.lastNodeInvokeResult.getFieldInfos();
        final List<Map<String, Object>> lastResult = this.lastNodeInvokeResult.getResult();
        final List<String> needFields = selectList.stream().map(t -> t.getExpr().toString()).collect(Collectors.toList());
        // 只允许有一个*
        if (needFields.contains("*")) {
            if (needFields.size() != 1) {
                Asserts.throwException("*不允许和其他列一起出现,请指定列!");
            }
            return lastNodeInvokeResult;
        }
        List<FieldInfo> newFieldInfo = new ArrayList<>();
        Set<String> newFieldNameSet = new HashSet<>();
        Map<String, FieldInfo> fieldInfoMap = lastFieldInfos.stream().collect(Collectors.toMap(FieldInfo::getFieldName, t -> t));
        for (String needFieldName : needFields) {
            /*2.如果结果列存在A.* 或者B.* 则通过tableName回溯寻找表来源,进行一个列表的拼*/
            if (needFieldName.endsWith("*")) {
                String needTableName = needFieldName.substring(0, needFieldName.indexOf('.') + 1);
                lastFieldInfos.stream().filter(lastFieldInfo -> Objects.equal(lastFieldInfo.getTableName(), needTableName)).forEach(lastFieldInfo -> {
                    FieldInfo fieldInfo = dealLastFieldInfo(newFieldNameSet, lastFieldInfo);
                    newFieldInfo.add(fieldInfo);
                    newFieldNameSet.add(fieldInfo.getFieldName());
                });
                continue;
            }
            /*3.如果结果列存在子查询, 则暂时不支持*/
            if (needFieldName.startsWith("&")) {

                NodeInvokeResult nodeInvokeResult = lastAllPlanResult.get(Long.parseLong(needFieldName.substring(1)));
                List<FieldInfo> specialLastFieldInfos = nodeInvokeResult.getFieldInfos();
                Asserts.assertTrue(specialLastFieldInfos != null && specialLastFieldInfos.size() == 1, "映射时需要有且仅有一个字段来映射");
                FieldInfo fieldInfo = specialLastFieldInfos.get(0);
                newFieldInfo.add(fieldInfo);
                newFieldNameSet.add(fieldInfo.getFieldName());
                continue;
            }

            /*4.如果结果列为 A.name 则通过tableName回溯寻找表来源,拼装*/

            String key = StringUtil.cleanQuotation(needFieldName);
            FieldInfo fieldInfo = fieldInfoMap.get(key);
            if (fieldInfo == null) {
                Asserts.throwException("未找到字段:" + key);
            }
            fieldInfo = dealLastFieldInfo(newFieldNameSet, fieldInfo);
            newFieldInfo.add(fieldInfo);
            newFieldNameSet.add(fieldInfo.getFieldName());
        }

        final List<Map<String, Object>> newResultList = lastResult.stream().map(t -> {
            Map<String, Object> newResult = new HashMap<>(selectList.size());
            for (Entry<String, Object> entry : t.entrySet()) {
                if (MysqlUtil.ignoreCaseAndQuotesContains(needFields, entry.getKey()) || needFields.contains("*")) {
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

    /**
     * 处理新字段
     *
     * @param newFieldNameSet
     * @param lastFieldInfo
     */
    private FieldInfo dealLastFieldInfo(Set<String> newFieldNameSet, FieldInfo lastFieldInfo) {
        // 名称和之前重复了
        if (!newFieldNameSet.contains(lastFieldInfo.getFieldName())) {
            return lastFieldInfo;
        }
        Integer fieldIndex = StringUtil.subFieldIndex(lastFieldInfo.getFieldName());
        int index;
        if (fieldIndex == null) {
            index = 1;
        } else {
            index = fieldIndex + 1;
        }
        return lastFieldInfo.copyWithNewFieldName(index);

    }
}
