package indi.uhyils.mysql.pojo.DTO;

import java.io.Serializable;

/**
 * 数据库信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月01日 09时19分
 */
public class DatabaseInfo implements Serializable {

    /**
     * SCHEMA所属目录的名称。该值始终为def
     */
    private String catalogName;

    /**
     * 数据库名称
     */
    private String schemaName;

    /**
     * 数据库编码
     */
    private String defaultCharacterSetName;

    /**
     * 数据库排序规则
     */
    private String defaultCollationName;

    /**
     * 此值始终为NULL
     */
    private String sqlPath;

    /**
     * 加密类型
     */
    private String defaultEncryption;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getDefaultCharacterSetName() {
        return defaultCharacterSetName;
    }

    public void setDefaultCharacterSetName(String defaultCharacterSetName) {
        this.defaultCharacterSetName = defaultCharacterSetName;
    }

    public String getDefaultCollationName() {
        return defaultCollationName;
    }

    public void setDefaultCollationName(String defaultCollationName) {
        this.defaultCollationName = defaultCollationName;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    public String getDefaultEncryption() {
        return defaultEncryption;
    }

    public void setDefaultEncryption(String defaultEncryption) {
        this.defaultEncryption = defaultEncryption;
    }
}
