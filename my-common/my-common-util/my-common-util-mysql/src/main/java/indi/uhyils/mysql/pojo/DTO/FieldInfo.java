package indi.uhyils.mysql.pojo.DTO;

import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.FieldTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.util.MysqlUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 14时11分
 */
public class FieldInfo {

    /**
     * 目录名称(恒为def)
     */
    private static final byte[] DIR_NAME = MysqlUtil.varString("def");

    /**
     * 字符编码
     */
    private static final byte[] CHAR_SET = new byte[]{(byte) 0xff, 0x00};

    /**
     * 填充值
     */
    private static final byte[] FILL_VALUE = new byte[]{0x0c};

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


    public FieldInfo(String dbName, String tableName, String tableRealName, String fieldName, String fieldRealName, int length, int index, FieldTypeEnum fieldType, short fieldMark, byte accuracy) {
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
    }

    public byte[] toFieldBytes() {
        MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        List<byte[]> results = new ArrayList<>();
        int count = 0;
        // 目录名称
        results.add(DIR_NAME);
        count += DIR_NAME.length;
        // 数据库名称
        byte[] e = MysqlUtil.varString(dbName != null ? dbName : mysqlTcpInfo.getDatabase());
        results.add(e);
        count += e.length;
        // 数据表名称
        byte[] e1 = MysqlUtil.varString(tableName);
        results.add(e1);
        count += e1.length;
        // 数据表原始名称
        byte[] e2 = MysqlUtil.varString(tableRealName);
        results.add(e2);
        count += e2.length;
        // 列字段名称
        byte[] e3 = MysqlUtil.varString(fieldName);
        results.add(e3);
        count += e3.length;
        // 列字段原始名称
        byte[] e4 = MysqlUtil.varString(fieldRealName);
        results.add(e4);
        count += e4.length;
        // 填充值
        results.add(FILL_VALUE);
        count += FILL_VALUE.length;
        // 字符编码
        results.add(CHAR_SET);
        count += CHAR_SET.length;
        // 列字段长度
        byte[] e5 = new byte[]{MysqlUtil.toBytes(length, 1)[0], 0x00, 0x00, 0x00};
        results.add(e5);
        count += e5.length;
        // 列字段类型
        byte[] e6 = new byte[]{fieldType.getCode()};
        results.add(e6);
        count += e6.length;
        // 列字段标志
        byte[] e7 = MysqlUtil.toBytes(fieldMark, 2);
        results.add(e7);
        count += e7.length;
        // 整型值精度
        byte[] e8 = new byte[]{accuracy};
        results.add(e8);
        count += e8.length;
        // 填充值
        byte[] e9 = {0x00, 0x00};
        results.add(e9);
        count += e9.length;
        return MysqlUtil.mergeListBytes(results, count);
    }

    /**
     * 根据新名字复制一个字段
     *
     * @param newFieldName
     *
     * @return
     */
    public FieldInfo copyWithNewFieldName(String newFieldName) {
        return new FieldInfo(this.dbName, this.tableName, this.tableRealName, newFieldName, this.fieldRealName, this.length, this.index, this.fieldType, this.fieldMark, this.accuracy);
    }

    /**
     * 根据新信息复制一个字段
     *
     * @param newTableName 新表名
     * @param newFieldName 新字段名
     *
     * @return
     */
    public FieldInfo copyWithNewFieldName(String newTableName, String newFieldName) {
        return new FieldInfo(this.dbName, newTableName, this.tableRealName, newFieldName, this.fieldRealName, this.length, this.index, this.fieldType, this.fieldMark, this.accuracy);
    }

    /**
     * 根据新名字复制一个字段
     *
     * @param index 复制为第几个
     *
     * @return
     */
    public FieldInfo copyWithNewFieldName(Integer index) {
        return copyWithNewFieldName(this.fieldName + "(" + index + ")");
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getIndex() {
        return index;
    }

    public String getTableName() {
        return tableName;
    }

    /**
     * 获取 表名.列表格式
     *
     * @return
     */
    public String getTableNameDotFieldName() {
        if (StringUtils.isNotEmpty(tableName)) {
            return tableName + "." + fieldName;
        }
        return fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldInfo fieldInfo = (FieldInfo) o;
        return length == fieldInfo.length && index == fieldInfo.index && fieldMark == fieldInfo.fieldMark && accuracy == fieldInfo.accuracy && Objects.equals(dbName, fieldInfo.dbName) && Objects.equals(tableName, fieldInfo.tableName) && Objects.equals(tableRealName, fieldInfo.tableRealName) && Objects.equals(fieldName, fieldInfo.fieldName) && Objects.equals(fieldRealName, fieldInfo.fieldRealName) && fieldType == fieldInfo.fieldType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbName, tableName, tableRealName, fieldName, fieldRealName, length, index, fieldType, fieldMark, accuracy);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("dbName", dbName)
            .append("tableName", tableName)
            .append("tableRealName", tableRealName)
            .append("fieldName", fieldName)
            .append("fieldRealName", fieldRealName)
            .append("length", length)
            .append("index", index)
            .append("fieldType", fieldType)
            .append("fieldMark", fieldMark)
            .append("accuracy", accuracy)
            .toString();
    }
}
