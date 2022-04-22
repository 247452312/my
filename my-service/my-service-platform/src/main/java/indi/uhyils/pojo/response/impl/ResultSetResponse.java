package indi.uhyils.pojo.response.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enums.MysqlServerStatusEnum;
import indi.uhyils.pojo.DTO.FieldInfo;
import indi.uhyils.pojo.response.AbstractMysqlResponse;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
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

    public ResultSetResponse(MysqlTcpInfo mysqlTcpInfo, List<FieldInfo> fields, JSONArray jsonInfo, MysqlServerStatusEnum serverStatus, int warnCount) {
        super(mysqlTcpInfo);
        this.fields = fields;
        this.jsonInfo = jsonInfo;
        this.serverStatus = serverStatus;
        this.warnCount = warnCount;
    }

    public ResultSetResponse(MysqlTcpInfo mysqlTcpInfo, List<FieldInfo> fields, JSONArray jsonInfo, MysqlServerStatusEnum serverStatus) {
        this(mysqlTcpInfo, fields, jsonInfo, serverStatus, mysqlTcpInfo.warnCount());
    }

    public ResultSetResponse(MysqlTcpInfo mysqlTcpInfo, List<FieldInfo> fields, JSONArray jsonInfo) {
        this(mysqlTcpInfo, fields, jsonInfo, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, mysqlTcpInfo.warnCount());
    }

    @Override
    public byte getFirstByte() {
        return (byte) 0xFF;
    }

    @Override
    public List<byte[]> toByteNoMarkIndex() {
        List<byte[]> results = new ArrayList<>(5);
        List<byte[]> fields = toFields();
        byte[] eof = toEof();
        List<byte[]> rowDatas = toRowData();
        // todo 这里不正确, 正确的resultSet应该返回多个数组, 1.col条数 2.col信息们 3.eof 4.result们 5.eof
        /*1.col条数*/
        results.add(MysqlUtil.mergeLengthCodedBinary(fields.size()));
        /*2.col信息*/
        results.addAll(fields);
        /*3.eof*/
        results.add(eof);
        /*4.result*/
        results.addAll(rowDatas);
        /*5.eof*/
        results.add(eof);
        return results;
    }

    /**
     * 获取列信息
     *
     * @return
     */
    private List<byte[]> toFields() {
        List<byte[]> results = new ArrayList<>(jsonInfo.size());
        for (FieldInfo field : fields) {
            byte[] bytes = field.toFieldBytes();
            results.add(bytes);
        }
        return results;
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
    private List<byte[]> toRowData() {
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
        return results;
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
        Asserts.throwException("mysql 数据暂未支持类型:" + obj.getClass().getName());
        return null;
    }

}
