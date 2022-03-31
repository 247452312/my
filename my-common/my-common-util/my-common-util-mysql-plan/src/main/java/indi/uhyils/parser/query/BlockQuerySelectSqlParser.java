package indi.uhyils.parser.query;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.expr.MySqlCharExpr;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.pojo.MySqlListExpr;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


/**
 * 常规查询语句解析类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 14时18分
 */
@Component
public class BlockQuerySelectSqlParser extends AbstractSelectSqlParser {


    @Override
    protected boolean doCanParse(SQLSelectStatement sql) {
        SQLSelectQuery query = sql.getSelect().getQuery();
        return query instanceof MySqlSelectQueryBlock;
    }

    @Override
    protected List<MysqlPlan> doParse(SQLSelectStatement sql) {
        return parseSelect(sql.getSelect());
    }

    private List<MysqlPlan> parseSelect(SQLSelect select) {
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        List<MysqlPlan> result = new ArrayList<>();
        // 如果 from 不是常规from 则转换from为另一个执行计划
        SQLTableSource sqlTableSource = transFrom(result, query.getFrom());
        query.setFrom(sqlTableSource);
        // 如果查询数据不是常规数据, 则转换为执行计划或者转换
        List<SQLSelectItem> sqlSelectItems = parseSelectList(result, query.getSelectList());
        // 解析where条件, 即入参
        List<SQLBinaryOpExpr> sqlBinaryOpExprs = parseSQLExprWhere(result, query.getWhere());
        // 制作执行计划
        List<MysqlPlan> mysqlPlans = makePlan(sqlBinaryOpExprs, sqlSelectItems, query.getFrom());
        result.addAll(mysqlPlans);
        return result;
    }

    private List<SQLSelectItem> parseSelectList(List<MysqlPlan> result, List<SQLSelectItem> selectList) {
        return selectList.stream().map(t -> {
            SQLExpr expr = t.getExpr();
            // 常规,直接返回
            if (expr instanceof SQLPropertyExpr || expr instanceof SQLIdentifierExpr || expr instanceof SQLAllColumnExpr) {
                return t;
            }
            if (expr instanceof SQLQueryExpr) {
                String sql = expr.toString();
                MysqlPlan newPlan = reExecute(sql, plans -> {
                    Asserts.assertTrue(plans != null && plans.size() == 1, "子查询不唯一");
                    return plans.get(0);
                });
                result.add(newPlan);
                return new SQLSelectItem(new MySqlCharExpr("&" + newPlan.getId()), t.getAlias());
            }
            Asserts.assertTrue(false, "查询报错,子查询类型找不到:{}", t.toString());
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * 制作执行计划
     *
     * @param params 条件-入参
     * @param result 出参
     * @param from   目标表(多个)
     *
     * @return
     */
    @NotNull
    private List<MysqlPlan> makePlan(List<SQLBinaryOpExpr> params, List<SQLSelectItem> result, SQLTableSource from) {
//        MysqlPlan outPlan = new MysqlPlanImpl();
//        if (CollectionUtil.isNotEmpty(params)) {
//            List<String> param = params.stream().map(t -> t.getLeft().toString()).collect(Collectors.toList());
//            outPlan.setParamNames(param);
//            JSONArray params = new JSONArray();
//            for (SQLBinaryOpExpr sqlBinaryOpExpr : params) {
//                params.add(PlanUtil.parasSQLBinaryOpExpr(sqlBinaryOpExpr));
//            }
//            outPlan.setParams(params);
//        }
//        outPlan.setTable(from);
//        outPlan.setParamNames(result.stream().map(t -> t.getExpr().toString()).collect(Collectors.toList()));
//        return outPlan;
        return Collections.emptyList();
    }

    /**
     * 转换where为正常逻辑
     *
     * @param where
     *
     * @return
     */
    private List<SQLBinaryOpExpr> parseSQLExprWhere(List<MysqlPlan> plans, SQLExpr where) {
        if (where == null) {
            return null;
        }
        List<SQLBinaryOpExpr> result = new ArrayList<>();
        if (where instanceof SQLBinaryOpExpr) {
            return parseSqlBinaryOpExprWhere(plans, (SQLBinaryOpExpr) where, result);
        }
        if (where instanceof SQLInSubQueryExpr) {
            SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) where;
            SQLExpr expr = sqlInSubQueryExpr.getExpr();
            SQLSelect subQuery = sqlInSubQueryExpr.getSubQuery();
            List<MysqlPlan> mysqlPlans = parseSelect(subQuery);
            Asserts.assertTrue(CollectionUtil.isNotEmpty(mysqlPlans), "解析plan为空:{}", subQuery);
            plans.addAll(mysqlPlans);
            MysqlPlan mysqlPlan = mysqlPlans.get(0);
            return Arrays.asList(new SQLBinaryOpExpr(expr, SQLBinaryOperator.Equality, new MySqlCharExpr(mysqlPlan.getId() + "&")));
        }
        if (where instanceof SQLInListExpr) {
            SQLInListExpr sqlInListExpr = (SQLInListExpr) where;
            SQLExpr expr = sqlInListExpr.getExpr();
            List<SQLExpr> targetList = sqlInListExpr.getTargetList();
            MySqlListExpr mySqlListExpr = new MySqlListExpr(targetList);
            return Arrays.asList(new SQLBinaryOpExpr(expr, SQLBinaryOperator.Equality, mySqlListExpr));
        }
        Asserts.assertTrue(false, "sql_where解析错误,没有找到解析类型:{}", where);
        return null;
    }

    private List<SQLBinaryOpExpr> parseSqlBinaryOpExprWhere(List<MysqlPlan> plans, SQLBinaryOpExpr whereSqlBinaryOpExpr, List<SQLBinaryOpExpr> sqlBinaryOpExprs) {
        SQLExpr left = whereSqlBinaryOpExpr.getLeft();
        SQLExpr right = whereSqlBinaryOpExpr.getRight();
        if (!(left instanceof SQLBinaryOpExpr) && !(right instanceof SQLBinaryOpExpr)) {
            sqlBinaryOpExprs.add(whereSqlBinaryOpExpr);
            return sqlBinaryOpExprs;
        }
        List<SQLBinaryOpExpr> leftSqlBinaryOpExprs = parseSQLExprWhere(plans, left);
        sqlBinaryOpExprs.addAll(leftSqlBinaryOpExprs);
        List<SQLBinaryOpExpr> rightSqlBinaryOpExprs = parseSQLExprWhere(plans, right);
        sqlBinaryOpExprs.addAll(rightSqlBinaryOpExprs);
        return sqlBinaryOpExprs;
    }

    /**
     * 转换from为正常的逻辑
     *
     * @param plans
     * @param from
     */
    private SQLTableSource transFrom(List<MysqlPlan> plans, SQLTableSource from) {
        if (!(from instanceof SQLExprTableSource)) {
            return reExecute(from.toString(), parse -> {
                Asserts.assertTrue(CollectionUtil.isNotEmpty(parse), "解析plan为空:{}", from.toString());
                plans.addAll(parse);
                MysqlPlan fromFirstPlan = parse.get(0);
                long index = fromFirstPlan.getId();
                return new SQLExprTableSource(new MySqlCharExpr("&" + index), from.getAlias());
            });
        }
        return from;
    }


}
