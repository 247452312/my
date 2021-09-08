package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.LogUtil;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月07日 14时02分
 */
public class SelectSql extends Sql {

    private final SQLSelect sqlSelect;

    public SelectSql(Sql sql) {
        super(sql.sql, sql.ignoreTables);
        AssertUtil.assertTrue(SqlType.SELECT.equals(sql.sqlType), MessageFormat.format("类型错误{0} -> {1}", sql.sqlType.name(), SqlType.UPDATE.name()));
        SQLSelectStatement sqlStatement = (SQLSelectStatement) sql.sqlStatement;
        this.sqlSelect = sqlStatement.getSelect();
    }


    public Boolean isUnion() {
        return isUnion(sqlSelect.getQuery());
    }

    public List<SQLOrderBy> order() {
        return null;
    }

    public List<SQLLimit> limit() {
        return null;
    }

    public List<SQLSelectItem> selectList() {
        List<SQLSelectItem> result = new ArrayList<>();
        for (SQLSelectQueryBlock blockQuery : blockQuerys()) {
            List<SQLSelectItem> sqlSelectItems = selectList(blockQuery);
            result.addAll(sqlSelectItems);
        }
        return result;
    }

    public List<SQLSelectItem> selectList(SQLSelectQueryBlock query) {
        List<SQLSelectItem> result = new ArrayList<>();
        List<SQLSelectItem> selectList = query.getSelectList();
        result.addAll(selectList);
        // sql部分
        for (SQLSelectItem sqlSelectItem : selectList) {
            if (sqlSelectItem.getExpr() instanceof SQLQueryExpr) {
                SQLQueryExpr expr = (SQLQueryExpr) sqlSelectItem.getExpr();
                List<SQLSelectItem> sqlSelectItems = selectList(expr.getSubQuery().getQueryBlock());
                result.addAll(sqlSelectItems);
            }
        }
        SQLTableSource from = query.getFrom();

        // 如果from语句是子查询
        if (from instanceof SQLSubqueryTableSource) {
            SQLSubqueryTableSource subqueryTableSource = (SQLSubqueryTableSource) from;
            SQLSelectQueryBlock queryBlock = subqueryTableSource.getSelect().getQueryBlock();
            List<SQLSelectItem> sqlSelectItems = selectList(queryBlock);
            result.addAll(sqlSelectItems);
        }
        return result;
    }

    public void fillDeleteFlag(Boolean deleteFlag) {
        List<SQLSelectQueryBlock> queryBlocks = blockQuerys();
        for (SQLSelectQueryBlock queryBlock : queryBlocks) {
            fillDeleteFlag(queryBlock);
        }
    }
    public void fillDeleteFlag() {
        fillDeleteFlag(false);
    }

    @Override
    public String sql() {
        super.sql();
        this.sql = sqlSelect.toString();
        LogUtil.info("change++++\n" + sql);
        return this.sql;
    }

    public List<SQLSelectQueryBlock> blockQuerys() {
        return blockQuerys(sqlSelect.getQuery());
    }


}
