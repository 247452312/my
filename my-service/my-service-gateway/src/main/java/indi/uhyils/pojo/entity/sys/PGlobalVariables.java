package indi.uhyils.pojo.entity.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.mysql.content.MysqlGlobalVariables;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.GlobalVariablesInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月22日 09时30分
 */
public class PGlobalVariables implements SysTable {


    /**
     * 入参
     */
    private final Map<String, Object> params;

    /**
     * mysql全局系统参数
     */
    private MysqlGlobalVariables mysqlGlobalVariables;


    public PGlobalVariables(Map<String, Object> params) {
        this.params = params.entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toLowerCase(), Entry::getValue));
        this.mysqlGlobalVariables = SpringUtil.getBean(MysqlGlobalVariables.class);
    }

    @Override
    public NodeInvokeResult getResult() {

        final Optional<UserDTO> userOptional = UserInfoHelper.get();
        if (!userOptional.isPresent()) {
            throw Asserts.makeException("未登录");
        }
        final String variableName = (String) params.get("variable_name");

        final JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(mysqlGlobalVariables));
        final List<Map<String, Object>> newResults = new ArrayList<>();
        jsonObject.entrySet().stream().filter(t -> {
            final String key = t.getKey();
            return MysqlUtil.likeMatching(key, variableName);
        }).forEach(t -> {
            final GlobalVariablesInfo globalVariablesInfo = new GlobalVariablesInfo();
            globalVariablesInfo.setVariableName(t.getKey());
            globalVariablesInfo.setVariableValue(t.getValue());
            newResults.add(JSONObject.parseObject(JSONObject.toJSONString(globalVariablesInfo)));
        });

        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
        if (CollectionUtil.isNotEmpty(newResults)) {
            final List<Map<String, Object>> tempResults = new ArrayList<>();
            final Map<String, Object> first = newResults.get(0);
            final Map<String, String> fieldNameMap = first.keySet().stream().collect(Collectors.toMap(t -> t, t -> StringUtil.toUnderline(t).toUpperCase()));
            for (Map<String, Object> newResult : newResults) {
                Map<String, Object> tempNewResultMap = new HashMap<>(newResult.size());
                for (Entry<String, Object> newResultItem : newResult.entrySet()) {
                    final String key = newResultItem.getKey();
                    final Object value = newResultItem.getValue();
                    tempNewResultMap.put(fieldNameMap.get(key), value);
                }
                tempResults.add(tempNewResultMap);
            }
            newResults.clear();
            newResults.addAll(tempResults);
        }
        nodeInvokeResult.setResult(newResults);
        final List<FieldInfo> fieldInfos = new ArrayList<>();
        fieldInfos.add(new FieldInfo("performance_schema", "global_variables", "global_variables", "VARIABLE_NAME", "VARIABLE_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("performance_schema", "global_variables", "global_variables", "VARIABLE_VALUE", "VARIABLE_VALUE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }
}
