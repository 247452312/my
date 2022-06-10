package indi.uhyils.pojo.tool;


import indi.uhyils.util.kpro.KproStringUtil;
import java.util.List;
import java.util.Objects;

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
     * class名称
     */
    private String className;

    /**
     * table描述
     */
    private String tableComment;

    /**
     * 列信息
     */
    private List<ColumnInfo> colums;

    /**
     * 主键
     */
    private ColumnInfo onlyKey;


    public TableInfo(String tableName, String tableComment, List<ColumnInfo> colums, ColumnInfo onlyKey) {
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.colums = colums;
        this.onlyKey = onlyKey;
        this.className = KproStringUtil.dealDbNameToJavaFileName(tableName);
    }

    public TableInfo() {
    }

    public String getTableName() {
        return tableName;
    }


    public List<ColumnInfo> getColums() {
        return colums;
    }

    public ColumnInfo getColumByName(String name) {
        for (ColumnInfo colum : colums) {
            if (Objects.equals(colum.getName(), name)) {
                return colum;
            }
        }
        return null;
    }

    public ColumnInfo getOnlyKey() {
        return onlyKey;
    }

    public void setOnlyKey(ColumnInfo onlyKey) {
        this.onlyKey = onlyKey;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getClassName() {
        return className;
    }

}
