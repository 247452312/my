package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
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


    public ResultMappingPlanImpl(Map<String, String> headers, List<SQLSelectItem> selectList) {
        super(headers, selectList);
    }


    @Override
    public NodeInvokeResult invoke() {
        final List<FieldInfo> fieldInfos = this.lastNodeInvokeResult.getFieldInfos();
        final List<Map<String, Object>> lastResult = this.lastNodeInvokeResult.getResult();
        final List<String> needField = selectList.stream().map(SQLSelectItem::toString).collect(Collectors.toList());

        final List<FieldInfo> newFieldInfo = fieldInfos.stream().filter(t -> needField.contains(t.getFieldName())).collect(Collectors.toList());
        final List<Map<String, Object>> newResultList = lastResult.stream().map(t -> {
            Map<String, Object> newResult = new HashMap<>(selectList.size());
            for (Entry<String, Object> entry : t.entrySet()) {
                if (needField.contains(entry.getKey())) {
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
