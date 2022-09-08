package indi.uhyils.mysql.pojo.DTO;

import indi.uhyils.mysql.enums.TableTypeEnum;
import java.io.Serializable;
import java.util.Date;

/**
 * 表信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月08日 09时30分
 */
public class TableInfo implements Serializable {

    /**
     * 结构信息,固定为def
     */
    private String tableCatalog = "def";

    /**
     * 所属数据库名称
     */
    private String tableSchema;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 创建选项
     */
    private String createOptions;

    /**
     * 表的注释、备注
     */
    private String tableComment;

    /**
     * 表类型
     */
    private TableTypeEnum tableType;

    /**
     * 引擎类型 恒为 InnoDB
     */
    private String engine = "InnoDB";

    /**
     * 版本 恒为10
     */
    private Float version = 10f;

    /**
     * 行格式 恒为Dynamic
     */
    private String rowFormat = "Dynamic";


    /**
     * 表行数,恒为0, 因为无法分辨
     */
    private Integer tableRows = 0;


    /**
     * 平均行数量,恒为0,因为无法分辨
     */
    private Integer avgRowLength = 0;

    /**
     * 数据长度
     */
    private Integer dataLength = 16384;


    /**
     * 最大数据长度
     */
    private Integer maxDataLength = 0;

    /**
     * 索引长度
     */
    private Integer indexLength = 0;

    /**
     * 空间碎片
     */
    private Integer dataFree = 0;

    /**
     * 做自增主键的自动增量当前值
     */
    private Integer autoIncrement = 0;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 检查时间
     */
    private Date checkTime;

    /**
     * 表的字符校验编码集
     */
    private String tableCollation = "utf8mb4_general_ci";

    /**
     * 校验和
     */
    private String checksum;




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

    public TableTypeEnum getTableType() {
        return tableType;
    }

    public void setTableType(TableTypeEnum tableType) {
        this.tableType = tableType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    public Integer getTableRows() {
        return tableRows;
    }

    public void setTableRows(Integer tableRows) {
        this.tableRows = tableRows;
    }

    public Integer getAvgRowLength() {
        return avgRowLength;
    }

    public void setAvgRowLength(Integer avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getMaxDataLength() {
        return maxDataLength;
    }

    public void setMaxDataLength(Integer maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    public Integer getIndexLength() {
        return indexLength;
    }

    public void setIndexLength(Integer indexLength) {
        this.indexLength = indexLength;
    }

    public Integer getDataFree() {
        return dataFree;
    }

    public void setDataFree(Integer dataFree) {
        this.dataFree = dataFree;
    }

    public Integer getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Integer autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getCreateOptions() {
        return createOptions;
    }

    public void setCreateOptions(String createOptions) {
        this.createOptions = createOptions;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
