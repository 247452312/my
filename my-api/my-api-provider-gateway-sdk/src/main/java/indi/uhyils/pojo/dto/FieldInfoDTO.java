package indi.uhyils.pojo.dto;

import java.io.Serializable;

/**
 * 字段信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月29日 09时03分
 */
public class FieldInfoDTO implements Serializable {

    /**
     * 所属库名
     */
    private String database;

    /**
     * 物理表名
     */
    private String realTable;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 小数位
     */
    private Integer decimals;

    /**
     * 字段类型
     *
     * @see indi.uhyils.mysql.enums.FieldTypeEnum
     */
    private Byte type;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }


    public String getRealTable() {
        return realTable;
    }

    public void setRealTable(String realTable) {
        this.realTable = realTable;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
