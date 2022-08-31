package indi.uhyils.plan.parser.query;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.ast.expr.SQLValuableExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource.JoinType;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.expr.MySqlCharExpr;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.PlanFactory;
import indi.uhyils.plan.pojo.MySqlListExpr;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PlanFactory planFactory;


    private List<SQLSelectItem> parseSelectList(List<MysqlPlan> result, List<SQLSelectItem> selectList, Map<String, String> headers) {
        return selectList.stream().map(t -> {
            SQLExpr expr = t.getExpr();
            // 常规,直接返回
            if (expr instanceof SQLPropertyExpr || expr instanceof SQLIdentifierExpr || expr instanceof SQLAllColumnExpr) {
                return t;
            }
            if (expr instanceof SQLQueryExpr) {
                String sql = expr.toString();
                MysqlPlan newPlan = reExecute(sql, headers, plans -> {
                    Asserts.assertTrue(plans != null && plans.size() == 1, "子查询不唯一");
                    return plans.get(0);
                });
                result.add(newPlan);
                return new SQLSelectItem(new MySqlCharExpr("&" + newPlan.getId()), t.getAlias());
            }
            if (expr instanceof SQLMethodInvokeExpr) {
                final SQLMethodInvokeExpr sqlMethodInvokeExpr = (SQLMethodInvokeExpr) expr;
                final String methodName = sqlMethodInvokeExpr.getMethodName();
                final List<SQLExpr> arguments = sqlMethodInvokeExpr.getArguments();
                MysqlPlan newPlan = planFactory.buildMethodInvokePlan(CollectionUtil.copyList(result), headers, methodName, arguments);
                result.add(newPlan);
                return new SQLSelectItem(new MySqlCharExpr("&" + newPlan.getId()), t.getAlias());
            }
            Asserts.throwException("查询报错,子查询类型找不到:{},内容为:{}", t.getClass().getName(), t.toString());
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * 制作执行计划
     *
     * @param froms 目标表(多个)
     *
     * @return
     */
    @NotNull
    private List<MysqlPlan> makePlan(List<MysqlPlan> plans, SqlTableSourceBinaryTree froms, Map<String, String> headers) {
        List<MysqlPlan> resultPlan = new ArrayList<>();
        if (froms.isLevel()) {
            MysqlPlan mysqlPlan = planFactory.buildBlockQuerySelectSqlPlan(CollectionUtil.copyList(plans), froms, headers, new HashMap<>());
            resultPlan.add(mysqlPlan);
            plans.add(mysqlPlan);
            return resultPlan;
        } else {
            JoinType joinType = froms.getJoinType();

            switch (joinType) {
                case INNER_JOIN:
                    return makeInnerJoin(plans, froms, headers);
                case LEFT_OUTER_JOIN:
                    return makeLeftJoin(plans, froms, headers);
                case RIGHT_OUTER_JOIN:
                    return makeRightJoin(plans, froms, headers);
                default:
                    Asserts.throwException("无指定连表方案");
                    return null;
            }
        }
    }

    @NotNull
    private List<MysqlPlan> makeRightJoin(List<MysqlPlan> plans, SqlTableSourceBinaryTree froms, Map<String, String> headers) {
        List<MysqlPlan> resultPlan = new ArrayList<>();
        List<MysqlPlan> rightPlan = makePlan(plans, froms.getRightTree(), headers);
        resultPlan.addAll(rightPlan);
        plans.addAll(rightPlan);
        List<MysqlPlan> leftPlan = makePlan(plans, froms.getLeftTree(), headers);
        resultPlan.addAll(leftPlan);
        plans.addAll(leftPlan);

        final List<Long> leftId = leftPlan.stream().map(MysqlPlan::getId).collect(Collectors.toList());
        final List<Long> rightId = rightPlan.stream().map(MysqlPlan::getId).collect(Collectors.toList());

        MysqlPlan sqlPlan = planFactory.buildRightJoinSqlPlan(CollectionUtil.copyList(plans), headers, leftId, rightId);
        resultPlan.add(sqlPlan);
        plans.add(sqlPlan);
        return resultPlan;
    }

    @NotNull
    private List<MysqlPlan> makeLeftJoin(List<MysqlPlan> plans, SqlTableSourceBinaryTree froms, Map<String, String> headers) {
        List<MysqlPlan> resultPlan = new ArrayList<>();
        List<MysqlPlan> leftPlan = makePlan(plans, froms.getLeftTree(), headers);
        resultPlan.addAll(leftPlan);
        List<MysqlPlan> rightPlan = makePlan(plans, froms.getRightTree(), headers);
        resultPlan.addAll(rightPlan);

        final List<Long> leftId = leftPlan.stream().map(MysqlPlan::getId).collect(Collectors.toList());
        final List<Long> rightId = rightPlan.stream().map(MysqlPlan::getId).collect(Collectors.toList());

        MysqlPlan sqlPlan = planFactory.buildLeftJoinSqlPlan(CollectionUtil.copyList(plans), headers, leftId, rightId);
        resultPlan.add(sqlPlan);
        plans.add(sqlPlan);
        return resultPlan;
    }

    @NotNull
    private List<MysqlPlan> makeInnerJoin(List<MysqlPlan> plans, SqlTableSourceBinaryTree froms, Map<String, String> headers) {
        List<MysqlPlan> resultPlan = new ArrayList<>();
        List<MysqlPlan> leftPlan = makePlan(plans, froms.getLeftTree(), headers);
        resultPlan.addAll(leftPlan);
        plans.addAll(leftPlan);
        List<MysqlPlan> rightPlan = makePlan(plans, froms.getRightTree(), headers);
        resultPlan.addAll(rightPlan);
        plans.addAll(rightPlan);

        final List<Long> leftId = leftPlan.stream().map(MysqlPlan::getId).collect(Collectors.toList());
        final List<Long> rightId = rightPlan.stream().map(MysqlPlan::getId).collect(Collectors.toList());

        MysqlPlan sqlPlan = planFactory.buildInnerJoinSqlPlan(CollectionUtil.copyList(plans), headers, leftId, rightId);
        resultPlan.add(sqlPlan);
        plans.add(sqlPlan);
        return resultPlan;
    }

    /**
     * 转换where为正常逻辑
     *
     * @param where
     *
     * @return
     */
    private List<SQLBinaryOpExpr> parseSQLExprWhere(List<MysqlPlan> plans, SQLExpr where, Map<String, String> headers) {
        if (where == null) {
            return null;
        }
        List<SQLBinaryOpExpr> result = new ArrayList<>();
        if (where instanceof SQLBinaryOpExpr) {
            return parseSqlBinaryOpExprWhere(plans, (SQLBinaryOpExpr) where, result, headers);
        }
        if (where instanceof SQLInSubQueryExpr) {
            SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) where;
            SQLExpr expr = sqlInSubQueryExpr.getExpr();
            SQLSelect subQuery = sqlInSubQueryExpr.getSubQuery();
            List<MysqlPlan> mysqlPlans = parseSelect(subQuery, headers);
            Asserts.assertTrue(CollectionUtil.isNotEmpty(mysqlPlans), "解析plan为空:{}", subQuery);
            plans.addAll(mysqlPlans);
            MysqlPlan mysqlPlan = mysqlPlans.get(0);
            return Arrays.asList(new SQLBinaryOpExpr(expr, SQLBinaryOperator.Equality, new MySqlCharExpr("&" + mysqlPlan.getId())));
        }
        if (where instanceof SQLInListExpr) {
            SQLInListExpr sqlInListExpr = (SQLInListExpr) where;
            SQLExpr expr = sqlInListExpr.getExpr();
            List<SQLExpr> targetList = sqlInListExpr.getTargetList();
            MySqlListExpr mySqlListExpr = new MySqlListExpr(targetList);
            return Arrays.asList(new SQLBinaryOpExpr(expr, SQLBinaryOperator.Equality, mySqlListExpr));
        }
        Asserts.throwException("sql_where解析错误,没有找到解析类型:{}", where);
        return null;
    }

    private List<SQLBinaryOpExpr> parseSqlBinaryOpExprWhere(List<MysqlPlan> plans, SQLBinaryOpExpr whereSqlBinaryOpExpr, List<SQLBinaryOpExpr> sqlBinaryOpExprs, Map<String, String> headers) {
        SQLExpr left = whereSqlBinaryOpExpr.getLeft();
        SQLExpr right = whereSqlBinaryOpExpr.getRight();
        if (whereSqlBinaryOpExpr.getOperator().isRelational()) {
            if (right instanceof SQLValuableExpr) {
                sqlBinaryOpExprs.add(whereSqlBinaryOpExpr);
                return sqlBinaryOpExprs;
            }
            if (right instanceof SQLQueryExpr) {
                final List<MysqlPlan> mysqlPlans = reExecute(right.toString(), headers, (Consumer<List<MysqlPlan>>) plans::addAll);
                final MysqlPlan lastMysqlPlan = mysqlPlans.get(mysqlPlans.size() - 1);
                SQLBinaryOpExpr sqlBinaryOpExpr = new SQLBinaryOpExpr(left, whereSqlBinaryOpExpr.getOperator(), new MySqlCharExpr("&" + lastMysqlPlan.getId()));
                sqlBinaryOpExprs.add(sqlBinaryOpExpr);
                return sqlBinaryOpExprs;
            }
            Asserts.throwException("未知的where条件类型:{},条件内容:{}", right.getClass().getName(), right.toString());

        }
        List<SQLBinaryOpExpr> leftSqlBinaryOpExprs = parseSQLExprWhere(plans, left, headers);
        sqlBinaryOpExprs.addAll(leftSqlBinaryOpExprs);
        List<SQLBinaryOpExpr> rightSqlBinaryOpExprs = parseSQLExprWhere(plans, right, headers);
        sqlBinaryOpExprs.addAll(rightSqlBinaryOpExprs);
        return sqlBinaryOpExprs;
    }

    /**
     * 转换from为正常的逻辑
     *
     * @param plans
     * @param from
     */
    @NotNull
    private SqlTableSourceBinaryTree transFrom(List<MysqlPlan> plans, SQLTableSource from, List<SQLBinaryOpExpr> where, Map<String, String> headers) {
        if (from instanceof SQLJoinTableSource) {
            SQLJoinTableSource sqlJoinTableSource = (SQLJoinTableSource) from;
            JoinType joinType = sqlJoinTableSource.getJoinType();
            SqlTableSourceBinaryTree lefts = transFrom(plans, sqlJoinTableSource.getLeft(), where, headers);
            SqlTableSourceBinaryTree rights = transFrom(plans, sqlJoinTableSource.getRight(), where, headers);

            switch (joinType) {
                case JOIN:
                case COMMA:
                case INNER_JOIN:
                    return pool.getOrCreateObject(lefts, rights, JoinType.INNER_JOIN);
                case LEFT_OUTER_JOIN:
                    return pool.getOrCreateObject(lefts, rights, JoinType.LEFT_OUTER_JOIN);
                case RIGHT_OUTER_JOIN:
                    return pool.getOrCreateObject(rights, lefts, JoinType.LEFT_OUTER_JOIN);
                default:
                    Asserts.throwException("sql连表条件不支持:{}", joinType.name_lcase);
                    return null;
            }

        } else if (!(from instanceof SQLExprTableSource)) {
            SQLExprTableSource sqlExprTableSource = reExecute(from.toString(), headers, parse -> {
                Asserts.assertTrue(CollectionUtil.isNotEmpty(parse), "解析plan为空:{}", from.toString());
                plans.addAll(parse);
                MysqlPlan lastPlan = parse.get(parse.size() - 1);
                long lastPlanId = lastPlan.getId();
                return new SQLExprTableSource(new MySqlCharExpr("&" + lastPlanId), from.getAlias());
            });
            return pool.getOrCreateObject(sqlExprTableSource, where);
        } else {
            return pool.getOrCreateObject(from, where);
        }
    }

    /**
     * 解析select语句
     *
     * @param select
     *
     * @return
     */
    private List<MysqlPlan> parseSelect(SQLSelect select, Map<String, String> headers) {
        // 1. 处理where中的子查询
        // 2.处理from后需要查询的条件
        // 3.selectList 查询字段的子查询
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        final ArrayList<MysqlPlan> plans = new ArrayList<>();
        final List<SQLBinaryOpExpr> where = parseSQLExprWhere(plans, query.getWhere(), headers);

        makePlan(plans, transFrom(plans, query.getFrom(), where, headers), headers);
        final List<SQLSelectItem> selectList = parseSelectList(plans, query.getSelectList(), headers);
        // 添加结果字段映射节点
        addResultMappingPlan(plans, headers, selectList);
        return plans;
    }

    /**
     * 结果字段映射节点添加
     *
     * @param plans
     * @param selectList
     */
    private void addResultMappingPlan(ArrayList<MysqlPlan> plans, Map<String, String> headers, List<SQLSelectItem> selectList) {
        ResultMappingPlan resultMappingPlan = planFactory.buildResultMappingPlan(CollectionUtil.copyList(plans), headers, selectList);
        plans.add(resultMappingPlan);
    }

    @Override
    protected boolean doCanParse(SQLSelectStatement sql) {
        SQLSelectQuery query = sql.getSelect().getQuery();
        return query instanceof MySqlSelectQueryBlock;
    }

    @Override
    protected List<MysqlPlan> doParse(SQLSelectStatement sql, Map<String, String> headers) {
        return parseSelect(sql.getSelect(), headers);
    }


}