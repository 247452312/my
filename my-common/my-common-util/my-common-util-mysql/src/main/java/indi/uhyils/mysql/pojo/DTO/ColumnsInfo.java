package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;

/**
 * 列信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月08日 14时40分
 */
public class ColumnsInfo implements Serializable {

    /**
     * 架构 恒为def
     */
    private String tableCatalog = "def";

    /**
     * 库名称
     */
    private String tableSchema;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 字段标识。其实就是字段编号，从1开始往后排。
     */
    private Integer ordinalPosition;

    /**
     * 系统提供的数据类型 里面的值是字符串,比如varchar,float,int
     */
    private String dataType;

    /**
     * datetime 类型字段显示为0 其他的显示为null
     */
    private Integer datetimePrecision;

    /**
     * 列类型这个类型比data_type列所指定的更加详细，如data_type 是int 而column_type 就有可以能是int(11)
     */
    private String columnType;

    /**
     * 权限
     */
    private String privileges;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 列的默认值(未知的)
     */
    private String columnDefault = "";

    /**
     * 列的为空性。如果列允许 NULL，那么该列返回 YES。否则，返回 NO
     */
    private String isNullable = "NO";


    /**
     * 以字符为单位的最大长度，适于二进制数据、字符数据，或者文本和图像数据。否则，返回 NULL。有关更多信息，请参见数据类型
     */
    private Integer characterMaximumLength = 0;

    /**
     * 以字节为单位的最大长度，适于二进制数据、字符数据，或者文本和图像数据。否则，返回 NULL。
     */
    private Integer characterOctetLength = 0;

    /**
     * 近似数字数据、精确数字数据、整型数据或货币数据的精度。否则，返回 NULL。
     */
    private Integer numericPrecision = 0;

    /**
     * 近似数字数据、精确数字数据、整数数据或货币数据的小数位数。否则，返回 NULL。
     */
    private Integer numericScale = 0;


    /**
     * 字段字符集名称。比如utf8。
     */
    private String characterSetName = "urf8mb4";

    /**
     * 字符集排序规则。
     * <p>
     * 比如utf8_general_ci，是不区分大小写一种排序规则。utf8_general_cs，是区分大小写的排序规则。
     */
    private String collationName = "比如utf8mb4_general_ci";


    /**
     * 列上的索引类型 主键-->PRI  | 唯一索引 -->UNI  一般索引 -->MUL
     */
    private String columnKey = "MUL";

    /**
     * 额外列选项
     */
    private String extra = "";


    /**
     * 组合字段的公式
     */
    private String generationExpression;

    /**
     *
     */
    private String srsId;

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(Integer datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public Integer getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(Integer characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public Integer getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Integer characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public Integer getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Integer getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Integer numericScale) {
        this.numericScale = numericScale;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getGenerationExpression() {
        return generationExpression;
    }

    public void setGenerationExpression(String generationExpression) {
        this.generationExpression = generationExpression;
    }

    public String getSrsId() {
        return srsId;
    }

    public void setSrsId(String srsId) {
        this.srsId = srsId;
    }
}
