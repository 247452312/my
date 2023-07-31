package indi.uhyils.pojo.entity.sys;

import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 存储子程序（存储程序和函数）的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月13日 08时55分
 */
public class IRoutines implements SysTable {

    public IRoutines(Map<String, Object> params) {
    }

    @Override
    public NodeInvokeResult getResult() {
        Optional<UserDTO> userOptional = UserInfoHelper.get();
        if (!userOptional.isPresent()) {
            throw Asserts.makeException("未登录");
        }

        List<Map<String, Object>> newResults = new ArrayList<>();

        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
        nodeInvokeResult.setResult(newResults);
        List<FieldInfo> fieldInfos = new ArrayList<>();

        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "SPECIFIC_NAME", "SPECIFIC_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "ROUTINE_CATALOG", "ROUTINE_CATALOG", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "ROUTINE_SCHEMA", "ROUTINE_SCHEMA", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "ROUTINE_NAME", "ROUTINE_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "ROUTINE_TYPE", "ROUTINE_TYPE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "DATA_TYPE", "DATA_TYPE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "CHARACTER_MAXIMUM_LENGTH", "CHARACTER_MAXIMUM_LENGTH", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "CHARACTER_OCTET_LENGTH", "CHARACTER_OCTET_LENGTH", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "NUMERIC_PRECISION", "NUMERIC_PRECISION", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "NUMERIC_SCALE", "NUMERIC_SCALE", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "DATETIME_PRECISION", "DATETIME_PRECISION", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "CHARACTER_SET_NAME", "CHARACTER_SET_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "COLLATION_NAME", "COLLATION_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "DTD_IDENTIFIER", "DTD_IDENTIFIER", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "ROUTINE_BODY", "ROUTINE_BODY", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "ROUTINE_DEFINITION", "ROUTINE_DEFINITION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "EXTERNAL_NAME", "EXTERNAL_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "EXTERNAL_LANGUAGE", "EXTERNAL_LANGUAGE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "PARAMETER_STYLE", "PARAMETER_STYLE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "IS_DETERMINISTIC", "IS_DETERMINISTIC", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "SQL_DATA_ACCESS", "SQL_DATA_ACCESS", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "SQL_PATH", "SQL_PATH", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "SECURITY_TYPE", "SECURITY_TYPE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "CREATED", "CREATED", 0, 1, FieldTypeEnum.FIELD_TYPE_TIMESTAMP, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "LAST_ALTERED", "LAST_ALTERED", 0, 1, FieldTypeEnum.FIELD_TYPE_TIMESTAMP, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "SQL_MODE", "SQL_MODE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "ROUTINE_COMMENT", "ROUTINE_COMMENT", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "DEFINER", "DEFINER", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "CHARACTER_SET_CLIENT", "CHARACTER_SET_CLIENT", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "COLLATION_CONNECTION", "COLLATION_CONNECTION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "routines", "routines", "DATABASE_COLLATION", "DATABASE_COLLATION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }
}
