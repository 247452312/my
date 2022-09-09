package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * mysql接口子表表(ProviderInterfaceMysql)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceMysqlDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * mysql对应的库名称
     */
    private String database;

    /**
     * mysql对应的表名称
     */
    private String table;

    /**
     * jdbc连接串
     */
    private String url;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("database", getDatabase())
            .append("table", getTable())
            .append("url", getUrl())
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

}
