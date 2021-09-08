package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import indi.uhyils.util.AssertUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 08时27分
 */
public class UpdateSql extends Sql {


    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private final SQLUpdateStatement sqlStatement;

    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public UpdateSql(Sql sql) {
        super(sql.sql);
        AssertUtil.assertTrue(SqlType.UPDATE.equals(sql.sqlType), "类型错误");
        this.sqlStatement = (SQLUpdateStatement) sql.sqlStatement;
    }

    public void addStringSet(String left, String right) {
        addSet(left, new SQLCharExpr(right));
    }

    public void addLongSet(String left, Long right) {
        addSet(left, new SQLIntegerExpr(right));
    }

    public void addDateSet(String left, Date right) {
        addStringSet(left, sdf.format(right));
    }

    public void addDateSet(String left, Long time) {
        addDateSet(left, new Date(time));
    }

    public void addSet(String left, SQLExpr right) {
        if (checkNameRepeat(left)) {
            return;
        }
        String alias = sqlStatement.getTableSource().getAlias();
        SQLUpdateSetItem item = new SQLUpdateSetItem();
        if (alias != null) {
            item.setColumn(new SQLPropertyExpr(alias, left));
        } else {
            item.setColumn(new SQLIdentifierExpr(left));
        }
        item.setValue(right);
        sqlStatement.addItem(item);
    }

    @Override
    public String sql() {
        return this.sql = String.valueOf(sqlStatement);
    }

    public void fillDeleteFlag() {
        SQLTableSource tableSource = sqlStatement.getTableSource();
        SQLBinaryOpExpr newWhere = getNewWhere(sqlStatement.getWhere(), Collections.singletonList((SQLExprTableSource) tableSource));
        sqlStatement.setWhere(newWhere);
        fillDeleteFlag(newWhere);

    }

    private boolean checkNameRepeat(String left) {
        List<SQLUpdateSetItem> items = sqlStatement.getItems();
        for (SQLUpdateSetItem item : items) {
            SQLExpr column = item.getColumn();
            String name;
            if (column instanceof SQLPropertyExpr) {
                name = ((SQLPropertyExpr) column).getName();
            } else {
                name = String.valueOf(column);
            }
            if (name.equals(left)) {
                return true;
            }
        }
        return false;
    }

    private void fillDeleteFlag(SQLBinaryOpExpr expr) {
        List<SQLSelectQueryBlock> queryBlocks = blockQuerys(expr);
        for (SQLSelectQueryBlock queryBlock : queryBlocks) {
            fillDeleteFlag(queryBlock);
        }
    }

    private List<SQLSelectQueryBlock> blockQuerys(SQLExpr expr) {
        if (expr instanceof SQLSelectQueryBlock) {
            return Arrays.asList((SQLSelectQueryBlock) expr);
        }
        List<SQLSelectQueryBlock> result = new ArrayList<>();

        for (SQLObject child : expr.getChildren()) {
            if (child instanceof SQLExpr) {
                List<SQLSelectQueryBlock> queryBlocks = blockQuerys((SQLExpr) child);
                result.addAll(queryBlocks);
            }
        }
        return result;

    }
}
