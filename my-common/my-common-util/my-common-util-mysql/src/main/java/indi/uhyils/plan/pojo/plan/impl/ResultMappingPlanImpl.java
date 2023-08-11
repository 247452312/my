package indi.uhyils.plan.pojo.plan.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.content.MysqlGlobalVariables;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.mysql.util.StringUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.enums.MysqlMethodEnum;
import indi.uhyils.plan.pojo.MySQLSelectItem;
import indi.uhyils.plan.pojo.plan.AbstractResultMappingPlan;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.plan.pojo.plan.JoinSqlPlan;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时33分
 */
public class ResultMappingPlanImpl extends AbstractResultMappingPlan {


    /**
     * 是否是每行一个结果
     */
    private final Boolean singleLine;

    /**
     * mysql系统变量
     */
    private final Map<String, Object> mysqlSystemVariables;

    /**
     * 当前映射之前最后一个查询执行计划的结果
     */
    private NodeInvokeResult lastQueryPlanResult;

    public ResultMappingPlanImpl(Map<String, String> headers, MysqlPlan lastMainPlan, List<MySQLSelectItem> selectList) {
        super(headers, lastMainPlan, selectList);
        MysqlGlobalVariables bean = SpringUtil.getBean(MysqlGlobalVariables.class);
        this.mysqlSystemVariables = JSONObject.parseObject(JSON.toJSONString(bean));
        // 是否是每行一个结果
        List<MysqlMethodEnum> allMethod = selectList.stream().filter(MySQLSelectItem::isMethodItem).map(MySQLSelectItem::method).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(allMethod)) {
            this.singleLine = true;
        } else {
            this.singleLine = allMethod.stream().allMatch(MysqlMethodEnum::getSingleLine);
        }
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
            MysqlPlan sourcePlan = nodeInvokeResult.getSourcePlan();
            if (sourcePlan instanceof BlockQuerySelectSqlPlan || sourcePlan instanceof JoinSqlPlan) {
                this.lastQueryPlanResult = nodeInvokeResult;
                break;
            }
        }
        Asserts.assertTrue(lastQueryPlanResult != null, "映射执行计划未找到上一个查询执行计划的结果");
    }

    @Override
    public NodeInvokeResult invoke() {
        /**
         * 整个mapping 应该是分为几种情况
         * 1.带有count,sum等函数的 需要行合并 则结果动态修正
         *  1.1 带有group 需要分组
         *  1.2 不带group 只有一行
         * 2.不需要合并,则可以直接根据上一次结果来直接映射 如果有子查询,则只需要判断size和上一次查询结果的行数来匹配后一一插入即可
         */

        /*如果是需要组合的情况*/
        if (!singleLine) {
            // 制作存在方法合并时候的结果
            return makeMethodNoSingleLine();
        }

        // 其他情况
        return makeOther();
    }

    /**
     * 除了方法合并的其他情况
     *
     * @return
     */
    private NodeInvokeResult makeOther() {
        List<String> needFields = selectList.stream().map(t -> t.getExpr().toString()).collect(Collectors.toList());
        /*1.如果结果列只有一个* 则直接返回 (如果除了*还有其他的 则报错)*/
        List<FieldInfo> lastFieldInfos = this.lastQueryPlanResult.getFieldInfos();
        List<Map<String, Object>> lastResult = this.lastQueryPlanResult.getResult();
        // 只允许有一个*
        if (needFields.contains("*")) {
            if (needFields.size() != 1) {
                Asserts.throwException("*不允许和其他列一起出现,请指定列!");
            }
            return lastQueryPlanResult;
        }
        List<FieldInfo> newFieldInfo = new ArrayList<>();
        Set<String> newFieldNameSet = new HashSet<>();
        Map<String, FieldInfo> fieldInfoMap = lastFieldInfos.stream().collect(Collectors.toMap(FieldInfo::getTableNameDotFieldName, t -> t));
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
            /*4.如果列是查询系统配置的,则返回*/
            if (needFieldName.startsWith("@@")) {
                newFieldInfo.add(new FieldInfo(MysqlContent.DUAL_DATABASES, "dual", "dual", needFieldName, needFieldName, 0, 0, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
                newFieldNameSet.add(needFieldName);
                continue;
            }

            /*4.如果结果列为 A.name 则通过tableName回溯寻找表来源,拼装*/

            String key = StringUtil.cleanQuotation(needFieldName);
            FieldInfo fieldInfo = queryFieldByKey(key, fieldInfoMap);
            if (fieldInfo == null) {
                Asserts.throwException("未找到字段:" + key);
            }
            fieldInfo = dealLastFieldInfo(newFieldNameSet, fieldInfo);
            newFieldInfo.add(fieldInfo);
            newFieldNameSet.add(fieldInfo.getFieldName());
        }
        List<Map<String, Object>> newResultList = lastResult.stream().map(t -> {
            Map<String, Object> newResult = new HashMap<>(selectList.size());
            for (Entry<String, Object> entry : t.entrySet()) {
                if (MysqlUtil.ignoreCaseAndQuotesContains(needFields, entry.getKey()) || needFields.contains("*")) {
                    newResult.put(entry.getKey(), entry.getValue());
                }
            }
            for (MySQLSelectItem mySQLSelectItem : selectList) {
                String variable = mySQLSelectItem.toString();
                if (variable.startsWith("@@")) {
                    String variableCleanName = variable.substring(2);
                    newResult.put(variable, mysqlSystemVariables.get(variableCleanName));
                }
            }
            return newResult;
        }).collect(Collectors.toList());

        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(this);
        nodeInvokeResult.setFieldInfos(newFieldInfo);
        nodeInvokeResult.setResult(newResultList);
        return nodeInvokeResult;
    }

    /**
     * 制作存在方法合并时候的结果
     *
     * @return
     */
    @NotNull
    private NodeInvokeResult makeMethodNoSingleLine() {
        List<String> needFields = selectList.stream().map(t -> t.getExpr().toString()).collect(Collectors.toList());
        int rowCount = -1;
        List<Map<String, Object>> result = new ArrayList<>();
        List<FieldInfo> fieldInfos = new ArrayList<>();
        Map<String, FieldInfo> fieldInfoMap = this.lastQueryPlanResult.getFieldInfos().stream().collect(Collectors.toMap(t -> StringUtil.cleanQuotation(t.getFieldName()), t -> t));
        /*每一个field都需要寻找是否是方法执行, 如果是正常的数据行,则需要判断容错查询参数是否开启*/
        for (String needField : needFields) {
            if (needField.startsWith("&")) {
                NodeInvokeResult nodeInvokeResult = lastAllPlanResult.get(Long.parseLong(needField.substring(1)));
                List<FieldInfo> specialLastFieldInfos = nodeInvokeResult.getFieldInfos();
                Asserts.assertTrue(specialLastFieldInfos != null && specialLastFieldInfos.size() == 1, "映射时需要有且仅有一个字段来映射");
                fieldInfos.add(specialLastFieldInfos.get(0));
                List<Map<String, Object>> fieldResult = nodeInvokeResult.getResult();
                if (rowCount == -1) {
                    rowCount = fieldResult.size();
                }
                Asserts.assertTrue(rowCount == fieldResult.size(), "多个方法并列的语句中,各个方法执行结果行数不同");
                if (CollectionUtil.isEmpty(result)) {
                    result.addAll(fieldResult);
                } else {
                    for (int i = 0; i < result.size(); i++) {
                        result.get(i).putAll(fieldResult.get(i));
                    }
                }
            } else {
                Boolean allowFault = config.getAllowFault();
                Asserts.assertTrue(allowFault, "不允许错误的sql语句, 在有合并意义的语句中不能出现实际行");

                /*获取对应字段的结果*/
                List<Map<String, Object>> lastResult = this.lastQueryPlanResult.getResult();
                Object newResult = null;
                if (CollectionUtil.isNotEmpty(lastResult)) {
                    Map<String, Object> first = lastResult.get(0);
                    for (Entry<String, Object> entry : first.entrySet()) {
                        if (Objects.equal(StringUtil.cleanQuotation(needField), StringUtil.cleanQuotation(entry.getKey())) || Objects.equal(needField, "*")) {
                            newResult = entry.getValue();
                        }
                    }
                }
                for (Map<String, Object> map : result) {
                    map.put(needField, newResult);
                }

                /*获取对应的字段信息*/
                fieldInfos.add(fieldInfoMap.get(StringUtil.cleanQuotation(needField)));

            }
        }
        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(this);
        nodeInvokeResult.setFieldInfos(fieldInfos);
        nodeInvokeResult.setResult(result);
        return nodeInvokeResult;
    }


    /**
     * 查询字段
     *
     * @param sourceFieldName 要查询的字段名字
     * @param fieldInfoMap    要查询的表名称
     */
    private FieldInfo queryFieldByKey(String sourceFieldName, Map<String, FieldInfo> fieldInfoMap) {
        /*查不到的原因: 多个表都有同一个字段名字,但是查询语句中没有指定表*/
        if (sourceFieldName.contains(".")) {
            return fieldInfoMap.get(sourceFieldName);
        }
        Set<String> keys = fieldInfoMap.keySet();
        String targetKey = null;
        for (String key : keys) {
            String[] split = key.split("\\.");
            Asserts.assertTrue(split.length == 2, "查询语句中字段名称:{},有问题", key);
            String fieldName = split[1];
            if (indi.uhyils.util.StringUtil.equalsIgnoreCase(fieldName, sourceFieldName)) {
                Asserts.assertTrue(targetKey == null, "查询语句中字段名称:{},重复,多个子表都存在此字段", key);
                targetKey = key;
            }
        }
        Asserts.assertTrue(StringUtils.isNotEmpty(targetKey), "未找到对应字段:{}", sourceFieldName);
        return fieldInfoMap.get(targetKey);
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
