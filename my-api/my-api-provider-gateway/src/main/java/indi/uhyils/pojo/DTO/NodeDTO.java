package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 转换节点表表(Node)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class NodeDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 对应低代码sql
     */
    private String sql;

    /**
     * 中间接口库名
     */
    private String database;

    /**
     * 中间接口表名
     */
    private String tableName;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("sql", getSql())
            .append("database", getDatabase())
            .append("tableName", getTableName())
            .toString();
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
