package indi.uhyils.mysql.pojo.DTO;

import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.util.MysqlUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 14时11分
 */
public class FieldInfo {

    /**
     * 目录名称(恒为def)
     */
    private static final byte[] DIR_NAME = MysqlUtil.mergeLengthCodedBinary("def");

    /**
     * 字符编码
     */
    private static final byte[] CHAR_SET = MysqlUtil.mergeLengthCodedBinary("utf8mb4");

    /**
     * 填充值
     */
    private static final byte[] FILL_VALUE = new byte[]{0x00};

    /**
     * 库名
     */
    private String dbName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表原始名称
     */
    private String tableRealName;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段真实名称
     */
    private String fieldRealName;

    /**
     * 列长度
     */
    private int length;

    /**
     * 本次返回在字段列表中的位置
     */
    private int index;


    /**
     * 列类型
     */
    private FieldTypeEnum fieldType;

    /**
     * 列标志
     */
    private short fieldMark;

    /**
     * 精度
     */
    private byte accuracy;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 对应执行计划的顺序
     */
    private long planIndex;

    public FieldInfo(String dbName, String tableName, String tableRealName, String fieldName, String fieldRealName, int length, int index, FieldTypeEnum fieldType, short fieldMark, byte accuracy, String defaultValue, long planIndex) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.tableRealName = tableRealName;
        this.fieldName = fieldName;
        this.fieldRealName = fieldRealName;
        this.length = length;
        this.index = index;
        this.fieldType = fieldType;
        this.fieldMark = fieldMark;
        this.accuracy = accuracy;
        this.defaultValue = defaultValue;
        this.planIndex = planIndex;
    }

    public byte[] toFieldBytes() {
        List<byte[]> results = new ArrayList<>();
        int count = 0;
        // 目录名称
        results.add(DIR_NAME);
        count += DIR_NAME.length;
        // 数据库名称
        byte[] e = MysqlUtil.mergeLengthCodedBinary(dbName);
        results.add(e);
        count += e.length;
        // 数据表名称
        byte[] e1 = MysqlUtil.mergeLengthCodedBinary(tableName);
        results.add(e1);
        count += e1.length;
        // 数据表原始名称
        byte[] e2 = MysqlUtil.mergeLengthCodedBinary(tableRealName);
        results.add(e2);
        count += e2.length;
        // 列字段名称
        byte[] e3 = MysqlUtil.mergeLengthCodedBinary(fieldName);
        results.add(e3);
        count += e3.length;
        // 列字段原始名称
        byte[] e4 = MysqlUtil.mergeLengthCodedBinary(fieldRealName);
        results.add(e4);
        count += e4.length;
        // 填充值
        results.add(FILL_VALUE);
        count += FILL_VALUE.length;
        // 字符编码
        results.add(CHAR_SET);
        count += CHAR_SET.length;
        // 列字段长度
        byte[] e5 = MysqlUtil.toBytes(length);
        results.add(e5);
        count += e5.length;
        // 列字段类型
        byte[] e6 = new byte[]{fieldType.getCode()};
        results.add(e6);
        count += e6.length;
        // 列字段标志
        byte[] e7 = MysqlUtil.toBytes(fieldMark);
        results.add(e7);
        count += e7.length;
        // 整型值精度
        byte[] e8 = new byte[]{accuracy};
        results.add(e8);
        count += e8.length;
        // 填充值
        results.add(FILL_VALUE);
        count += FILL_VALUE.length;
        // 默认值
        byte[] e9 = MysqlUtil.mergeLengthCodedBinary(defaultValue == null ? "" : defaultValue);
        results.add(e9);
        count += e9.length;
        return MysqlUtil.mergeListBytes(results, count);
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getIndex() {
        return index;
    }

    public long getPlanIndex() {
        return planIndex;
    }
}
