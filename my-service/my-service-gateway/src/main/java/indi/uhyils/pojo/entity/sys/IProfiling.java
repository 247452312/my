package indi.uhyils.pojo.entity.sys;

import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 可以用来分析每一条SQL在它执行的各个阶段的用时，注意这个表是session 级的，也就是说如果session1 开启了它；session2没有开启
 * <p>
 * 这个情况下session2 去查询只会返回一个空表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月02日 10时39分
 */
public class IProfiling extends AbstractSysTable {

    public IProfiling(Map<String, Object> params) {
        super(params);
    }

    @Override
    protected NodeInvokeResult doGetResultNoParams() {
        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);

        List<Map<String, Object>> result = new ArrayList<>();
        nodeInvokeResult.setResult(result);
        List<FieldInfo> fieldInfos = new ArrayList<>();
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "QUERY_ID", "QUERY_ID", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "SEQ", "SEQ", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "STATE", "STATE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "DURATION", "DURATION", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "CPU_USER", "CPU_USER", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "CPU_SYSTEM", "CPU_SYSTEM", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "CONTEXT_VOLUNTARY", "CONTEXT_VOLUNTARY", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "CONTEXT_INVOLUNTARY", "CONTEXT_INVOLUNTARY", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "BLOCK_OPS_IN", "BLOCK_OPS_IN", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "BLOCK_OPS_OUT", "BLOCK_OPS_OUT", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "MESSAGES_SENT", "MESSAGES_SENT", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "MESSAGES_RECEIVED", "MESSAGES_RECEIVED", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "PAGE_FAULTS_MAJOR", "PAGE_FAULTS_MAJOR", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "PAGE_FAULTS_MINOR", "PAGE_FAULTS_MINOR", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "SWAPS", "SWAPS", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "SOURCE_FUNCTION", "SOURCE_FUNCTION", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "SOURCE_FILE", "SOURCE_FILE", 0, 1, FieldTypeEnum.FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
        fieldInfos.add(new FieldInfo("information_schema", "profiling", "profiling", "SOURCE_LINE", "SOURCE_LINE", 0, 1, FieldTypeEnum.FIELD_TYPE_INT24, (short) 0, (byte) 0));
        nodeInvokeResult.setFieldInfos(fieldInfos);
        return nodeInvokeResult;
    }
}
