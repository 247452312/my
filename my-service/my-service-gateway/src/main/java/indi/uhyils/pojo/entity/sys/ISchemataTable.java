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
import indi.uhyils.util.GatewayUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 * inforamtion_schema库 SCHEMATA表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月01日 14时22分
 */
public class ISchemataTable implements SysTable {

    /**
     * 入参
     */
    private final Map<String, Object> params;

    private CallNodeService callNodeService;

    public ISchemataTable(Map<String, Object> params) {
        this.params = params.entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toLowerCase(), Entry::getKey));
        this.callNodeService = SpringUtil.getBean(CallNodeService.class);
    }


    @Override
    public NodeInvokeResult getResult() {
        final Object schemaName = params.get("schema_name");
        final ArrayList<Arg> args = new ArrayList<>();
        final Optional<UserDTO> userOptional = UserInfoHelper.get();
        userOptional.ifPresent(userDTO -> args.add(Arg.as(CallNodeDO::getCompanyId, Symbol.EQ, userDTO.getId())));
        List<CallNodeDTO> callNodeDTOS = callNodeService.queryNoPage(args);
        if (schemaName != null) {
            if (schemaName instanceof String) {
                callNodeDTOS = callNodeDTOS.stream().filter(t -> t.getUrl().startsWith(schemaName + MysqlContent.PATH_SEPARATOR)).collect(Collectors.toList());
            } else if (schemaName instanceof List) {
                final List<String> schemaNameStrList = (List<String>) schemaName;
                callNodeDTOS = callNodeDTOS.stream().filter(t -> containsLike(schemaNameStrList, t.getUrl())).collect(Collectors.toList());

            }
        }

        final List<Map<String, Object>> newResult = new ArrayList<>();

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
            final DatabaseInfo databaseInfo = new DatabaseInfo();
            databaseInfo.setCatalogName(MysqlContent.CATALOG_NAME);
            databaseInfo.setSchemaName(splitDataBaseUrl.getKey());
            databaseInfo.setDefaultCharacterSetName(MysqlContent.DEFAULT_CHARACTER_SET_NAME);
            databaseInfo.setDefaultCollationName(MysqlContent.DEFAULT_COLLATION_NAME);
            databaseInfo.setSqlPath(null);
            databaseInfo.setDefaultEncryption("NO");
            newResult.add(JSONObject.parseObject(JSONObject.toJSONString(databaseInfo)));
        });

        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult();
        nodeInvokeResult.setResult(newResult);
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
