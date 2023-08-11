package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * mysql接口子表(ProviderInterfaceMysql)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@TableName(value = "sys_provider_interface_mysql")
public class ProviderInterfaceMysqlDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * mysql对应的库名称
     */
    @TableField
    private String database;

    /**
     * mysql对应的表名称
     */
    @TableField
    private String table;

    /**
     * jdbc连接串
     */
    @TableField
    private String url;

    /**
     * 主表id
     */
    @TableField
    private Long fid;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("database", getDatabase())
            .append("table", getTable())
            .append("url", getUrl())
            .append("fid", getFid())
            .toString();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }
}
