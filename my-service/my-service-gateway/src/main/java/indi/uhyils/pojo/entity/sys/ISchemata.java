package indi.uhyils.pojo.entity.sys;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.enums.Symbol;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.DatabaseInfo;
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
 * information_schema库 SCHEMATA表
 * 提供了关于数据库中的库的信息。详细表述了某个库的名称，默认编码，排序规则。
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月01日 14时22分
 */
public class ISchemata extends AbstractSysTable {


    private final CallNodeService callNodeService;

    public ISchemata(Map<String, Object> params) {
        super(params);
        this.params = params.entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toLowerCase(), Entry::getValue));
        this.callNodeService = SpringUtil.getBean(CallNodeService.class);
    }


    @Override
    public NodeInvokeResult doGetResultNoParams() {
        Object schemaName = params.get("table_schema");
        ArrayList<Arg> args = new ArrayList<>();
        Optional<UserDTO> userOptional = UserInfoHelper.get();
        if (!userOptional.isPresent()) {
            throw Asserts.makeException("未登录");
        }
        args.add(Arg.as(CallNodeDO::getCompanyId, Symbol.EQ, userOptional.get().getId()));
        if (schemaName != null) {
            args.add(Arg.as(CallNodeDO::getUrl, Symbol.like, schemaName + "/%"));
        }
        List<CallNodeDTO> callNodeDTOS = callNodeService.queryNoPage(args);

        List<Map<String, Object>> newResults = new ArrayList<>();

        Set<String> dbSet = new HashSet<>();
        callNodeDTOS.stream().filter(t -> {
            String url = t.getUrl();
            Pair<String, String> splitDataBaseUrl = GatewayUtil.splitDataBaseUrl(url);
            String database = splitDataBaseUrl.getKey();
            if (dbSet.contains(database)) {
                return false;
            } else {
                dbSet.add(database);
                return true;
            }
        }).forEach(t -> {
            Pair<String, String> splitDataBaseUrl = GatewayUtil.splitDataBaseUrl(t.getUrl());
            DatabaseInfo databaseInfo = new DatabaseInfo();
            databaseInfo.setCatalogName(MysqlContent.CATALOG_NAME);
            databaseInfo.setSchemaName(splitDataBaseUrl.getKey());
            databaseInfo.setDefaultCharacterSetName(MysqlContent.DEFAULT_CHARACTER_SET_NAME);
            databaseInfo.setDefaultCollationName(MysqlContent.DEFAULT_COLLATION_NAME);
            databaseInfo.setSqlPath(null);
            databaseInfo.setDefaultEncryption("NO");
            newResults.add(JSONObject.parseObject(JSONObject.toJSONString(databaseInfo)));
        });

        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
        if (CollectionUtil.isNotEmpty(newResults)) {
            List<Map<String, Object>> tempResults = new ArrayList<>();
            Map<String, Object> first = newResults.get(0);
            Map<String, String> fieldNameMap = first.keySet().stream().collect(Collectors.toMap(t -> t, t -> StringUtil.toUnderline(t).toUpperCase()));
            for (Map<String, Object> newResult : newResults) {
                Map<String, Object> tempNewResultMap = new HashMap<>(newResult.size());
                for (Entry<String, Object> newResultItem : newResult.entrySet()) {
                    String key = newResultItem.getKey();
                    Object value = newResultItem.getValue();
                    tempNewResultMap.put(fieldNameMap.get(key), value);
                }
                tempResults.add(tempNewResultMap);
            }
            newResults.clear();
            newResults.addAll(tempResults);
        }
        nodeInvokeResult.setResult(newResults);
        List<FieldInfo> fieldInfos = new ArrayList<>();
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "CATALOG_NAME", "CATALOG_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "SCHEMA_NAME", "SCHEMA_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "DEFAULT_CHARACTER_SET_NAME", "DEFAULT_CHARACTER_SET_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "DEFAULT_COLLATION_NAME", "DEFAULT_COLLATION_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "SQL_PATH", "SQL_PATH", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "schemata", "schemata", "DEFAULT_ENCRYPTION", "DEFAULT_ENCRYPTION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }

    /**
     * 是否包含
     *
     * @param schemaNameStrList
     * @param schemaName
     *
     * @return
     */
    private boolean containsLike(List<String> schemaNameStrList, String schemaName) {
        boolean result = false;
        for (String s : schemaNameStrList) {
            if (schemaName.startsWith(s)) {
                result = true;
            }
        }
        return result;
    }
}
