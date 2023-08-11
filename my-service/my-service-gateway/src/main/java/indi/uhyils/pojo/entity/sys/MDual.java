package indi.uhyils.pojo.entity.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.mysql.content.MysqlGlobalVariables;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月11日 16时15分
 */
public class MDual extends AbstractSysTable {

    /**
     * mysql全局系统参数
     */
    private final MysqlGlobalVariables mysqlGlobalVariables;


    public MDual(Map<String, Object> params) {
        super(params);
        this.params = params.entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toLowerCase(), Entry::getValue));
        this.mysqlGlobalVariables = SpringUtil.getBean(MysqlGlobalVariables.class);
    }

    @Override
    public NodeInvokeResult doGetResultNoParams() {

        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(mysqlGlobalVariables));
        List<Map<String, Object>> newResults = new ArrayList<>();
        List<FieldInfo> fieldInfos = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();
        newResults.add(result);

        for (Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String fieldName = "@@" + key;
            result.put(fieldName, value);
            if (value instanceof Number) {
                fieldInfos.add(new FieldInfo("mysql", "dual", "dual", fieldName, fieldName, 0, 1, FieldTypeEnum.FIELD_TYPE_FLOAT, (short) 0, (byte) 0));
            } else {
                fieldInfos.add(new FieldInfo("mysql", "dual", "dual", fieldName, fieldName, 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
            }
        }

        return NodeInvokeResult.build(fieldInfos, newResults, null);
    }
}
