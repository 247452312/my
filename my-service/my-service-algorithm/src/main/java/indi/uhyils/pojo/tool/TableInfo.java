package indi.uhyils.pojo.tool;


import java.util.Map;

/**
 * 数据库表信息 model
 *
 * @author 商轶龙 <247452312@qq.com>
 * @copyright Copyright 2017-2027 商轶龙
 * @license ZPL (http://zpl.pub/v12)
 * @date 文件创建日期 2018-09-17 17:24
 */
public class TableInfo {

    /**
     * table名称
     */
    private String tableName;

    /**
     * 列信息
     */
    private Map<String, ColumnInfo> colums;

    /**
     * 主键
     */
    private ColumnInfo onlyKey;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ColumnInfo> getColums() {
        return colums;
    }

    public void setColums(Map<String, ColumnInfo> colums) {
        this.colums = colums;
    }

    public ColumnInfo getOnlyKey() {
        return onlyKey;
    }

    public void setOnlyKey(ColumnInfo onlyKey) {
        this.onlyKey = onlyKey;
    }

    public TableInfo(String tableName, Map<String, ColumnInfo> colums,
                     ColumnInfo onlyKey) {
        super();
        this.tableName = tableName;
        this.colums = colums;
        this.onlyKey = onlyKey;
    }

    public TableInfo() {
    }


}
