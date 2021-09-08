package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLNumberExpr;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement.ValuesClause;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import indi.uhyils.util.CollectionUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 12时57分
 */
public class InsertSql extends Sql {

    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    private SQLInsertStatement sqlStatement;

    public InsertSql(Sql sql) {
        super(sql.sql);
        this.sqlStatement = (SQLInsertStatement) sql.sqlStatement;
    }

    public void fillDeleteFlag() {
        SQLSelect query = sqlStatement.getQuery();
        if (query == null) {
            return;
        }
        List<SQLSelectQueryBlock> queryBlocks = blockQuerys(query.getQuery());
        for (SQLSelectQueryBlock queryBlock : queryBlocks) {
            fillDeleteFlag(queryBlock);
        }
    }

    @Override
    public String sql() {
        return this.sql = String.valueOf(sqlStatement);
    }

    public void addDateItem(String colName, long value) {
        addDateItem(colName, new Date(value));
    }

    public void addDateItem(String colName, Date value) {
        addItem(colName, new SQLCharExpr(sdf.format(value)));
    }

    public void addItem(String colName, SQLExpr value) {
        List<ValuesClause> valuesList = sqlStatement.getValuesList();
        if (CollectionUtil.isEmpty(valuesList)) {
            return;
        }
        // 去重
        boolean b = sqlStatement.getColumns().stream().anyMatch(t -> String.valueOf(t).equals(colName));
        if (b) {
            return;
        }
        sqlStatement.addColumn(new SQLIdentifierExpr(colName));
        for (ValuesClause valuesClause : valuesList) {
            valuesClause.addValue(value);
        }
    }

    public void addLongItem(String colName, Long value) {
        addItem(colName, new SQLNumberExpr(value));
    }
}
