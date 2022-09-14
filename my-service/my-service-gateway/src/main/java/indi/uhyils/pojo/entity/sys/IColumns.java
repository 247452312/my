package indi.uhyils.pojo.entity.sys;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.enums.Symbol;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.ColumnsInfo;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.DTO.CallNodeDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.service.CallNodeService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.GatewayUtil;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月08日 14时24分
 */
public class IColumns implements SysTable {

    /**
     * 入参
     */
    private final Map<String, Object> params;

    private final CallNodeService callNodeService;

    public IColumns(Map<String, Object> params) {
        this.params = params.entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toLowerCase(), Entry::getValue));
        this.callNodeService = SpringUtil.getBean(CallNodeService.class);
    }

    @Override
    public NodeInvokeResult getResult() {
        final Object schemaName = params.get("schema_name");
        final ArrayList<Arg> args = new ArrayList<>();
        final Optional<UserDTO> userOptional = UserInfoHelper.get();
        if (!userOptional.isPresent()) {
            throw Asserts.makeException("未登录");
        }
        args.add(Arg.as(CallNodeDO::getCompanyId, Symbol.EQ, userOptional.get().getId()));
        if (schemaName != null) {
            args.add(Arg.as(CallNodeDO::getUrl, Symbol.like, schemaName + "/%"));
        }
        List<CallNodeDTO> callNodeDTOS = callNodeService.queryWithAllNode(args);

        final List<Map<String, Object>> newResults = new ArrayList<>();

        Set<String> dbSet = new HashSet<>();
        callNodeDTOS.stream().filter(t -> {
            final String url = t.getUrl();
            final Pair<String, String> splitDataBaseUrl = GatewayUtil.splitDataBaseUrl(url);
            final String database = splitDataBaseUrl.getKey();
            if (dbSet.contains(database)) {
                return false;
            } else {
                dbSet.add(database);
                return true;
            }
        }).forEach(t -> {
            final Pair<String, String> splitDataBaseUrl = GatewayUtil.splitDataBaseUrl(t.getUrl());
            final ColumnsInfo columnsInfo = new ColumnsInfo();

            newResults.add(JSONObject.parseObject(JSONObject.toJSONString(columnsInfo)));
        });

        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult();
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
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "CATALOG_NAME", "CATALOG_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "SCHEMA_NAME", "SCHEMA_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "DEFAULT_CHARACTER_SET_NAME", "DEFAULT_CHARACTER_SET_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "DEFAULT_COLLATION_NAME", "DEFAULT_COLLATION_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "SQL_PATH", "SQL_PATH", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "DEFAULT_ENCRYPTION", "DEFAULT_ENCRYPTION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }
}
