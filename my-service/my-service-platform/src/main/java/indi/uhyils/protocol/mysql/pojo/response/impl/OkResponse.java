package indi.uhyils.protocol.mysql.pojo.response.impl;

import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.response.AbstractMysqlResponse;
import indi.uhyils.protocol.mysql.util.MysqlUtil;
import indi.uhyils.util.Asserts;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 20时38分
 */
public class OkResponse extends AbstractMysqlResponse {

    /**
     * sql类型
     */
    private SqlTypeEnum sqlTypeEnum;

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
    public OkResponse(MysqlHandler mysqlHandler, SqlTypeEnum sqlTypeEnum, long rowLength, long indexIdValue, MysqlServerStatusEnum serverStatus, int warnCount, String msg) {
        super(mysqlHandler);
        this.sqlTypeEnum = sqlTypeEnum;
        this.rowLength = rowLength;
        this.indexIdValue = indexIdValue;
        this.serverStatus = serverStatus;
        this.warnCount = warnCount;
        this.msg = msg;
    }


    public OkResponse(MysqlHandler mysqlHandler, SqlTypeEnum sqlTypeEnum) {
        super(mysqlHandler);
        this.sqlTypeEnum = sqlTypeEnum;
    }

    public OkResponse(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    public byte getFirstByte() {
        // ok报文恒为0x00
        return 0x00;
    }

    @Override
    public byte[] toByteNoMarkIndex() {
        Asserts.assertTrue(sqlTypeEnum != null);
        Asserts.assertTrue(sqlTypeEnum == null || sqlTypeEnum != SqlTypeEnum.QUERY, "查询不能返回OK消息");
        return mergeOk();
    }

    public SqlTypeEnum getSqlTypeEnum() {
        return sqlTypeEnum;
    }


    private byte[] mergeOk() {
        List<byte[]> listResult = new ArrayList<>();
        // 添加影响行数报文
        byte[] e = MysqlUtil.mergeLengthCodedBinary(rowLength);
        listResult.add(e);
        // 添加索引id值
        byte[] e1 = MysqlUtil.mergeLengthCodedBinary(indexIdValue);
        listResult.add(e1);
        if (serverStatus == null) {
            serverStatus = MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES;
        }
        // 添加服务器状态
        byte[] e2 = MysqlUtil.toBytes(serverStatus.getCode());
        listResult.add(e2);
        // 添加告警计数
        byte[] e3 = MysqlUtil.toBytes(warnCount, 2);
        listResult.add(e3);
        if (msg != null) {
            // 添加服务器消息
            byte[] bytes1 = msg.getBytes(StandardCharsets.UTF_8);
            listResult.add(bytes1);
        }
        return MysqlUtil.mergeListBytes(listResult);
    }


}
