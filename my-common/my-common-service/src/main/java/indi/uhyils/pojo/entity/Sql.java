package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月07日 10时51分
 */
public class Sql extends AbstractEntity {

    protected String sql;

    protected SQLStatement sqlStatement;

    protected SqlType sqlType;

    public Sql(String sql) {
        this.sql = sql;
    }

    public void parse() {
        if (sqlStatement == null) {
            this.sqlStatement = new MySqlStatementParser(sql).parseStatement();
        }
        if (sqlType != null) {
            return;
        }
        if (this.sqlStatement instanceof SQLSelectStatement) {
            this.sqlType = SqlType.SELECT;
        } else if (this.sqlStatement instanceof SQLUpdateStatement) {
            this.sqlType = SqlType.UPDATE;
        } else if (this.sqlStatement instanceof SQLInsertStatement) {
            this.sqlType = SqlType.INSERT;
        } else if (this.sqlStatement instanceof SQLDeleteStatement) {
            this.sqlType = SqlType.DELETE;
        }
    }

    public SqlType type() {
        return sqlType;
    }

    public Sql transformation() {
        parse();
        switch (sqlType) {
            case DELETE:
                return null;
            case INSERT:
                return null;
            case SELECT:
                return new SelectSql(this);
            case UPDATE:
                return null;
            default:
                return null;
        }
    }


    public enum SqlType {
        /**
         * 见名知义
         */
        SELECT,
        UPDATE,
        INSERT,
        DELETE
    }
}
