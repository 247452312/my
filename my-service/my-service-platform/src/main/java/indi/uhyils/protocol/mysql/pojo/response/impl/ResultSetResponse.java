package indi.uhyils.protocol.mysql.pojo.response.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.pojo.entity.FieldInfo;
import indi.uhyils.protocol.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 20时39分
 */
public class ResultSetResponse extends AbstractMysqlResponse {

    /**
     * 列类型
     */
    private List<FieldInfo> fields;

    /**
     * 要返回的信息
     */
    private JSONArray jsonInfo;

    /**
     * 数据库状态
     */
    private MysqlServerStatusEnum serverStatus;

    /**
     * 告警计数
     */
    private int warnCount;

    public ResultSetResponse(List<FieldInfo> fields, JSONArray jsonInfo, MysqlServerStatusEnum serverStatus, int warnCount) {
        this.fields = fields;
        this.jsonInfo = jsonInfo;
        this.serverStatus = serverStatus;
        this.warnCount = warnCount;
    }

    @Override
    public byte getFirstByte() {
        return (byte) 0xFF;
    }

    @Override
    public byte[] toByteNoMarkIndex() {
        List<byte[]> results = new ArrayList<>(5);
        results.add(toResultSetHeader());
        results.add(toFields());
        results.add(toEof());
        results.add(toRowData());
        results.add(toEof());
        return MysqlUtil.mergeListBytes(results);
    }

    /**
     * 获取结果头
     *
     * @return
     */
    private byte[] toResultSetHeader() {
        byte[] bytes = MysqlUtil.mergeLengthCodedBinary(jsonInfo.size());
        // 添加额外信息 恒为251
        byte[] result = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        result[bytes.length] = (byte) 251;
        return result;
    }

    /**
     * 获取列信息
     *
     * @return
     */
    private byte[] toFields() {
        List<byte[]> results = new ArrayList<>(jsonInfo.size());
        int count = 0;
        for (FieldInfo field : fields) {
            byte[] bytes = field.toFieldBytes();
            results.add(bytes);
            count += bytes.length;
        }
        return MysqlUtil.mergeListBytes(results, count);
    }

    /**
     * eof
     *
     * @return
     */
    private byte[] toEof() {
        List<byte[]> results = new ArrayList<>(3);
        results.add(new byte[]{(byte) 0xfe});
        results.add(MysqlUtil.buildFixedInt(2, warnCount));
        results.add(MysqlUtil.buildFixedInt(2, serverStatus.getCode()));
        return MysqlUtil.mergeListBytes(results);
    }

    /**
     * 转换为row数据
     *
     * @return
     */
    private byte[] toRowData() {
        List<byte[]> results = new ArrayList<>();

        for (int i = 0; i < jsonInfo.size(); i++) {
            JSONObject jsonObject = jsonInfo.getJSONObject(i);

            for (FieldInfo field : fields) {
                String fieldName = field.getFieldName();
                Object o = jsonObject.get(fieldName);
                byte[] byteData = transObjToByte(o);
                results.add(byteData);
            }
        }
        return MysqlUtil.mergeListBytes(results);
    }

    /**
     * 转换obj为data
     *
     * @param obj
     *
     * @return
     */
    private byte[] transObjToByte(Object obj) {
        if (obj instanceof Date) {
            Date dateValue = (Date) obj;
            return MysqlUtil.mergeLengthCodedBinary(dateValue.getTime());
        }
        if (obj instanceof String) {
            return MysqlUtil.mergeLengthCodedBinary((String) obj);
        }
        if (obj instanceof Long) {
            return MysqlUtil.mergeLengthCodedBinary((Long) obj);
        }
        if (obj instanceof Double) {
            return MysqlUtil.mergeLengthCodedBinary((double) obj);
        }
        Asserts.assertTrue(false, "mysql 数据暂未支持类型:" + obj.getClass().getName());
        return null;
    }

}
