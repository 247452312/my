package indi.uhyils.pojo.tool;

/**
 * 数据库列信息 model
 *
 * @author 商轶龙 <247452312@qq.com>
 * @copyright Copyright 2017-2027 商轶龙
 * @license ZPL (http://zpl.pub/v12)
 * @date 文件创建日期 2018-09-17 17:24
 */
public class ColumnInfo {

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 是不是key
     */
    private int keyType;

    /**
     * 列名称
     */
    private String name;

    /**
     * 首字母大写
     */
    private String bigName;

    /**
     * 首字母小写
     */
    private String smallName;

    /**
     * 列备注
     */
    private String remark;

    public ColumnInfo(String dataType, int keyType, String name) {
        this.dataType = dataType;
        this.keyType = keyType;
        this.name = name;
    }

    public ColumnInfo() {
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBigName() {
        return bigName;
    }

    public void setBigName(String bigName) {
        this.bigName = bigName;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
