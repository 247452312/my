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
 * 视图信息查询
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月13日 08时28分
 */
public class IParameters implements SysTable {


    public IParameters(Map<String, Object> params) {
    }

    @Override
    public NodeInvokeResult getResult() {
        final Optional<UserDTO> userOptional = UserInfoHelper.get();
        if (!userOptional.isPresent()) {
            throw Asserts.makeException("未登录");
        }

        final List<Map<String, Object>> newResults = new ArrayList<>();

        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
        nodeInvokeResult.setResult(newResults);
        final List<FieldInfo> fieldInfos = new ArrayList<>();

        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "SPECIFIC_CATALOG", "SPECIFIC_CATALOG", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "SPECIFIC_SCHEMA", "SPECIFIC_SCHEMA", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "SPECIFIC_NAME", "SPECIFIC_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "ORDINAL_POSITION", "ORDINAL_POSITION", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "PARAMETER_MODE", "PARAMETER_MODE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "PARAMETER_NAME", "PARAMETER_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "DATA_TYPE", "DATA_TYPE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "CHARACTER_MAXIMUM_LENGTH", "CHARACTER_MAXIMUM_LENGTH", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "CHARACTER_OCTET_LENGTH", "CHARACTER_OCTET_LENGTH", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "NUMERIC_PRECISION", "NUMERIC_PRECISION", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "NUMERIC_SCALE", "NUMERIC_SCALE", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "DATETIME_PRECISION", "DATETIME_PRECISION", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "CHARACTER_SET_NAME", "CHARACTER_SET_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "COLLATION_NAME", "COLLATION_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "DTD_IDENTIFIER", "DTD_IDENTIFIER", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "parameters", "parameters", "ROUTINE_TYPE", "ROUTINE_TYPE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }
}
