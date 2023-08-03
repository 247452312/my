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
 * 该表提供存储引擎相关的信息，主要用来确认数据库是否支持该存储引擎以及是否是默认的该表不是标准的INFORMATION_SCHEMA表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月14日 09时40分
 */
public class IEngines extends AbstractSysTable {

    public IEngines(Map<String, Object> params) {
        super(params);
    }

    @Override
    public NodeInvokeResult doGetResultNoParams() {
        Optional<UserDTO> userOptional = UserInfoHelper.get();
        if (!userOptional.isPresent()) {
            throw Asserts.makeException("未登录");
        }

        List<Map<String, Object>> newResults = new ArrayList<>();

        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
        nodeInvokeResult.setResult(newResults);
        List<FieldInfo> fieldInfos = new ArrayList<>();

        fieldInfos.add(new FieldInfo("information_schema", "engines", "engines", "ENGINE", "ENGINE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "engines", "engines", "SUPPORT", "SUPPORT", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "engines", "engines", "COMMENT", "COMMENT", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "engines", "engines", "TRANSACTIONS", "TRANSACTIONS", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "engines", "engines", "XA", "XA", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "engines", "engines", "SAVEPOINTS", "SAVEPOINTS", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }
}
