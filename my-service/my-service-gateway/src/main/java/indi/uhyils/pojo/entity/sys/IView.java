package indi.uhyils.pojo.entity.sys;

import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.service.CallNodeService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月16日 09时54分
 */
public class IView extends AbstractSysTable {


    private final CallNodeService callNodeService;

    public IView(Map<String, Object> params) {
        super(params);
        this.params = params.entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toLowerCase(), Entry::getValue));
        this.callNodeService = SpringUtil.getBean(CallNodeService.class);
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
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "TABLE_CATALOG", "TABLE_CATALOG", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "TABLE_SCHEMA", "TABLE_SCHEMA", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "TABLE_NAME", "TABLE_NAME", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "VIEW_DEFINITION", "VIEW_DEFINITION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "CHECK_OPTION", "CHECK_OPTION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "IS_UPDATABLE", "IS_UPDATABLE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "DEFINER", "DEFINER", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "SECURITY_TYPE", "SECURITY_TYPE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "CHARACTER_SET_CLIENT", "CHARACTER_SET_CLIENT", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "view", "view", "COLLATION_CONNECTION", "COLLATION_CONNECTION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }
}
