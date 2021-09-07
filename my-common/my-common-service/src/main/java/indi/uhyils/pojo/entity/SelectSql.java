package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月07日 14时02分
 */
public class SelectSql extends Sql {

    private SQLSelect sqlSelect;

    public SelectSql(Sql sql) {
        super(sql.sql);
        AssertUtil.assertTrue(SqlType.SELECT.equals(sql.sqlType), "类型错误");
        SQLSelectStatement sqlStatement = (SQLSelectStatement) sql.sqlStatement;
        this.sqlSelect = sqlStatement.getSelect();
    }

    public Boolean isUnion(SQLSelectQuery query) {
        return query instanceof SQLUnionQuery;
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

    public void fillDeleteFlag(SQLSelectQueryBlock queryBlock) {
        SQLExpr newWhereCondition = getNewWhereCondition(queryBlock.getFrom(), queryBlock.getWhere());
        if (newWhereCondition == null) {
            return;
        }
        queryBlock.setWhere(newWhereCondition);
    }

    private SQLExpr getNewWhereCondition(SQLTableSource from, SQLExpr where) {
        List<SQLExprTableSource> sqlExprTableSources = parseTables(from);
        if (CollectionUtil.isEmpty(sqlExprTableSources)) {
            return null;
        }
        return getNewWhere(where, sqlExprTableSources);
    }

    private List<SQLExprTableSource> parseTables(SQLTableSource table) {
        if (table == null) {
            return null;
        }
        if (table instanceof SQLExprTableSource) {
            SQLExprTableSource tableSource = (SQLExprTableSource) table;
            ArrayList<SQLExprTableSource> sqlExprTableSources = new ArrayList<>();
            sqlExprTableSources.add(tableSource);
            return sqlExprTableSources;
        }

        if (table instanceof SQLJoinTableSource) {
            SQLJoinTableSource joinTableSource = (SQLJoinTableSource) table;
            SQLTableSource left = joinTableSource.getLeft();
            SQLTableSource right = joinTableSource.getRight();
            List<SQLExprTableSource> sqlExprTableSources = parseTables(left);
            if (sqlExprTableSources == null) {
                sqlExprTableSources = new ArrayList<>();
            }
            List<SQLExprTableSource> c = parseTables(right);
            if (c != null) {
                sqlExprTableSources.addAll(c);
            }
            return sqlExprTableSources;
        }
        return new ArrayList<>();
    }

    private SQLBinaryOpExpr getNewWhere(SQLExpr where, List<SQLExprTableSource> tableSource) {
        String[] tableName = getTableName(tableSource);
        if (where == null) {
            return getCondition(tableName);
        }

        SQLBinaryOpExpr condition = new SQLBinaryOpExpr("mysql");
        condition.setLeft(where);
        condition.setOperator(SQLBinaryOperator.BooleanAnd);
        condition.setRight(getCondition(tableName));
        return condition;
    }

    private String[] getTableName(List<SQLExprTableSource> tableSource) {
        String[] results = new String[tableSource.size()];
        for (int i = 0; i < tableSource.size(); i++) {
            String tableName = tableSource.get(i).getAlias();
            if (StringUtils.isBlank(tableName)) {
                tableName = "";
            }
            results[i] = tableName;
        }

        return results;
    }

    /**
     * 根据表信息拼接tenantId 条件
     *
     * @param alias 表信息
     *
     * @return 拼接后的条件
     */
    private SQLBinaryOpExpr getCondition(String... alias) {
        SQLBinaryOpExpr allCondition = new SQLBinaryOpExpr("mysql");
        for (int i = 0; i < alias.length; i++) {
            SQLBinaryOpExpr thisTenantIdWhere = getTenantIdCondition(alias[i]);
            // 如果是最后一个且不是第一个则将当期table条件设置为右侧条件
            if (i > 0 && i == alias.length - 1) {
                allCondition.setOperator(SQLBinaryOperator.BooleanAnd);
                allCondition.setRight(thisTenantIdWhere);
                break;
            }
            // 如果是只有一个table 则直接设置最终条件为当期table条件
            if (alias.length == 1) {
                allCondition = thisTenantIdWhere;
                break;
            }
            if (allCondition.getLeft() == null) {
                allCondition.setLeft(thisTenantIdWhere);
            } else {
                SQLBinaryOpExpr condition = getAndCondition((SQLBinaryOpExpr)allCondition.getLeft(), thisTenantIdWhere);
                allCondition.setLeft(condition);
            }
        }
        return allCondition;
    }

    /**
     * 拼接and条件
     *
     * @param left 左侧条件
     * @param right 右侧条件
     * @return 拼接后的条件
     */
    private SQLBinaryOpExpr getAndCondition(SQLBinaryOpExpr left, SQLBinaryOpExpr right) {
        SQLBinaryOpExpr condition = new SQLBinaryOpExpr("mysql");
        condition.setLeft(left);
        condition.setOperator(SQLBinaryOperator.BooleanAnd);
        condition.setRight(right);
        return condition;
    }

    /**
     * 根据表信息拼接tenantId 条件
     *
     * @param tableNameInfo 表信息
     * @return 拼接后的条件
     */
    private SQLBinaryOpExpr getTenantIdCondition(String tableNameInfo) {
        SQLBinaryOpExpr tenantIdWhere = new SQLBinaryOpExpr("mysql");
        if (StringUtils.isEmpty(tableNameInfo)) {
            // 拼接新的条件
            tenantIdWhere.setOperator(SQLBinaryOperator.Equality);
            tenantIdWhere.setLeft(new SQLIdentifierExpr("delete_flag"));
            // 设置当前租户ID条件
            tenantIdWhere.setRight(new SQLIntegerExpr(0));
        } else {
            // 拼接别名条件
            tenantIdWhere.setLeft(new SQLPropertyExpr(tableNameInfo, "delete_flag"));
            tenantIdWhere.setOperator(SQLBinaryOperator.Equality);
            tenantIdWhere.setRight(new SQLIntegerExpr(0));
        }
        return tenantIdWhere;
    }


    public void fillDeleteFlag() {
        fillDeleteFlag(false);
    }

    public String sql() {
        return this.sql = sqlSelect.toString();
    }

    public List<SQLSelectQueryBlock> blockQuerys(SQLSelectQuery query) {
        List<SQLSelectQueryBlock> queryBlocks = new ArrayList<>();
        // union
        if (isUnion(query)) {
            SQLUnionQuery uniqueQuery = (SQLUnionQuery) query;
            List<SQLSelectQuery> relations = uniqueQuery.getRelations();
            for (SQLSelectQuery relation : relations) {
                List<SQLSelectQueryBlock> allBlockQuery = blockQuerys(relation);
                queryBlocks.addAll(allBlockQuery);
            }
            return queryBlocks;
        }

        SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) query;

        // 子查询
        List<SQLSelectItem> selectList = queryBlock.getSelectList();
        for (SQLSelectItem sqlSelectItem : selectList) {
            SQLExpr expr = sqlSelectItem.getExpr();
            if (expr instanceof SQLQueryExpr) {
                SQLQueryExpr queryExpr = (SQLQueryExpr) expr;
                queryBlocks.addAll(blockQuerys(queryExpr.getSubQuery().getQueryBlock()));
            }
        }
        // from语句中的子查询
        SQLTableSource from = queryBlock.getFrom();
        // 如果from语句是子查询
        if (from instanceof SQLSubqueryTableSource) {
            SQLSubqueryTableSource subqueryTableSource = (SQLSubqueryTableSource) from;
            queryBlocks.addAll(blockQuerys(subqueryTableSource.getSelect().getQueryBlock()));
        }
        SQLExpr where = queryBlock.getWhere();
        if (where != null) {
            List<SQLSelectQueryBlock> queryBlockInExpr = findQueryBlockInExpr(where);
            if (queryBlockInExpr != null) {
                queryBlocks.addAll(queryBlockInExpr);
            }
        }
        queryBlocks.add(queryBlock);
        return queryBlocks;
    }

    public List<SQLSelectQueryBlock> blockQuerys() {
        return blockQuerys(sqlSelect.getQuery());
    }

    private List<SQLSelectQueryBlock> findQueryBlockInExpr(SQLExpr expr) {
        List<SQLSelectQueryBlock> result = null;
        List<SQLObject> children = expr.getChildren();
        for (SQLObject child : children) {
            if (child instanceof SQLSelect) {
                List<SQLSelectQueryBlock> queryBlocks = blockQuerys(((SQLSelect) child).getQuery());
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.addAll(queryBlocks);
                continue;
            }
            if (child instanceof SQLExpr) {
                SQLExpr exprChild = (SQLExpr) child;
                List<SQLSelectQueryBlock> queryBlockInExpr = findQueryBlockInExpr(exprChild);
                if (queryBlockInExpr != null && result == null) {
                    result = new ArrayList<>();
                }
                if (queryBlockInExpr != null) {
                    result.addAll(queryBlockInExpr);
                }
            }
        }
        return result;
    }


}
