package indi.uhyils.protocol.mysql.pojo.response.impl;

import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.util.Asserts;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 20时38分
 */
public class OkResponse extends AbstractMysqlResponse {

    /**
     * sql类型
     */
    private final SqlTypeEnum sqlTypeEnum;

    /**
     * 受影响行数
     */
    private long rowLength;

    /**
     * 索引id值
     */
    private long indexIdValue;

    /**
     * 数据库状态
     */
    private MysqlServerStatusEnum serverStatus;

    /**
     * 告警计数
     */
    private int warnCount;

    /**
     * 带回的消息
     */
    private String msg;

    /**
     * @param sqlTypeEnum  sql类型
     * @param rowLength    受影响行数
     * @param indexIdValue 索引id值
     * @param serverStatus 数据库状态
     * @param warnCount    告警计数
     * @param msg          带回的消息
     */
    public OkResponse(SqlTypeEnum sqlTypeEnum, long rowLength, long indexIdValue, MysqlServerStatusEnum serverStatus, int warnCount, String msg) {
        this.sqlTypeEnum = sqlTypeEnum;
        this.rowLength = rowLength;
        this.indexIdValue = indexIdValue;
        this.serverStatus = serverStatus;
        this.warnCount = warnCount;
        this.msg = msg;
    }

    public OkResponse(SqlTypeEnum sqlTypeEnum) {
        this.sqlTypeEnum = sqlTypeEnum;
    }

    @Override
    public byte getFirstByte() {
        // ok报文恒为0x00
        return 0x00;
    }

    @Override
    public byte[] toByteNoMarkIndex() {
        Asserts.assertTrue(sqlTypeEnum != null);
        Asserts.assertTrue(sqlTypeEnum != SqlTypeEnum.QUERY, "查询不能返回OK消息");
        return MysqlUtil.mergeObjsToByte(rowLength, indexIdValue, serverStatus.getCode(), warnCount, msg);
    }

    public SqlTypeEnum getSqlTypeEnum() {
        return sqlTypeEnum;
    }


}
