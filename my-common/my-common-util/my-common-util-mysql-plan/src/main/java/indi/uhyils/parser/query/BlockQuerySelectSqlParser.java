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
import indi.uhyils.plan.AbstractMysqlPlan;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import indi.uhyils.pojo.MySqlListExpr;
import indi.uhyils.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    protected List<MysqlPlan> doParse(SQLSelectStatement sql, Map<Long, List<Map<String, Object>>> planResult) {
        return parseSelect(sql.getSelect(), planResult);
    }

    private List<MysqlPlan> parseSelect(SQLSelect select, Map<Long, List<Map<String, Object>>> planResult) {
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        List<SQLSelectItem> selectList = query.getSelectList();
        for (SQLSelectItem sqlSelectItem : selectList) {
            SQLExpr expr = sqlSelectItem.getExpr();
            String alias = sqlSelectItem.getAlias();
            int i = 1;
        }
        return null;
    }

    /**
     * 解析sql查询条件为map
     *
     * @param sqlBinaryOpExprs
     *
     * @return
     */
    private Map<String, Object> parseParam(List<SQLBinaryOpExpr> sqlBinaryOpExprs) {
        return null;
    }

    private List<SQLSelectItem> parseSelectList(List<MysqlPlan> result, List<SQLSelectItem> selectList, Map<Long, List<Map<String, Object>>> planResult) {
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
     * @param froms  目标表(多个)
     *
     * @return
     */
    @NotNull
    private List<MysqlPlan> makePlan(List<MysqlPlan> plans, Map<String, Object> params, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms, Map<Long, List<Map<String, Object>>> planResult) {
        if (froms.isLevel()) {
            long id = plans.get(plans.size() - 1).getId();
            MysqlPlan mysqlPlan = new BlockQuerySelectSqlPlan(id + 1, null, params, planResult);
            plans.add(mysqlPlan);
        } else {
            JoinType joinType = froms.getJoinType();

            switch (joinType) {
                case INNER_JOIN:
                    return makeInnerJoin(plans, params, result, froms, planResult);
                case LEFT_OUTER_JOIN:
                    return makeLeftJoin(plans, params, result, froms, planResult);
                case RIGHT_OUTER_JOIN:
                    return makeRightJoin(plans, params, result, froms, planResult);
                default:
                    Asserts.assertTrue(false, "无指定连表方案");
            }

        }
        return Collections.emptyList();
    }

    @NotNull
    private List<MysqlPlan> makeRightJoin(List<MysqlPlan> plans, Map<String, Object> params, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms, Map<Long, List<Map<String, Object>>> planResult) {
        List<MysqlPlan> rightPlan = makePlan(plans, params, result, froms.getRightTree(), planResult);
        plans.addAll(rightPlan);
        List<MysqlPlan> leftPlan = makePlan(plans, params, result, froms.getLeftTree(), planResult);
        plans.addAll(leftPlan);
        long id = plans.get(plans.size() - 1).getId();
        long leftId = leftPlan.get(leftPlan.size() - 1).getId();
        long right = rightPlan.get(rightPlan.size() - 1).getId();
        MysqlPlan sqlPlan = new RightJoinSqlPlan(id + 1, null, leftId, right, planResult);
        plans.add(sqlPlan);
        return plans;
    }

    @NotNull
    private List<MysqlPlan> makeLeftJoin(List<MysqlPlan> plans, Map<String, Object> params, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms, Map<Long, List<Map<String, Object>>> planResult) {
        List<MysqlPlan> leftPlan = makePlan(plans, params, result, froms.getLeftTree(), planResult);
        plans.addAll(leftPlan);
        List<MysqlPlan> rightPlan = makePlan(plans, params, result, froms.getRightTree(), planResult);
        plans.addAll(rightPlan);
        long id = plans.get(plans.size() - 1).getId();
        long leftId = leftPlan.get(leftPlan.size() - 1).getId();
        long right = rightPlan.get(rightPlan.size() - 1).getId();
        MysqlPlan sqlPlan = new LeftJoinSqlPlan(id + 1, null, leftId, right, planResult);
        plans.add(sqlPlan);
        return plans;
    }

    @NotNull
    private List<MysqlPlan> makeInnerJoin(List<MysqlPlan> plans, Map<String, Object> params, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms, Map<Long, List<Map<String, Object>>> planResult) {
        List<MysqlPlan> leftPlan = makePlan(plans, params, result, froms.getLeftTree(), planResult);
        plans.addAll(leftPlan);
        List<MysqlPlan> rightPlan = makePlan(plans, params, result, froms.getRightTree(), planResult);
        plans.addAll(rightPlan);
        long id = plans.get(plans.size() - 1).getId();
        long leftId = leftPlan.get(leftPlan.size() - 1).getId();
        long right = rightPlan.get(rightPlan.size() - 1).getId();
        MysqlPlan sqlPlan = new InnerJoinSqlPlan(id + 1, null, leftId, right, planResult);
        plans.add(sqlPlan);
        return plans;
    }

    /**
     * 转换where为正常逻辑
     *
     * @param where
     * @param planResult
     *
     * @return
     */
    private List<SQLBinaryOpExpr> parseSQLExprWhere(List<MysqlPlan> plans, SQLExpr where, Map<Long, List<Map<String, Object>>> planResult) {
        if (where == null) {
            return null;
        }
        List<SQLBinaryOpExpr> result = new ArrayList<>();
        if (where instanceof SQLBinaryOpExpr) {
            return parseSqlBinaryOpExprWhere(plans, (SQLBinaryOpExpr) where, result, planResult);
        }
        if (where instanceof SQLInSubQueryExpr) {
            SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) where;
            SQLExpr expr = sqlInSubQueryExpr.getExpr();
            SQLSelect subQuery = sqlInSubQueryExpr.getSubQuery();
            List<MysqlPlan> mysqlPlans = parseSelect(subQuery, planResult);
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

    private List<SQLBinaryOpExpr> parseSqlBinaryOpExprWhere(List<MysqlPlan> plans, SQLBinaryOpExpr whereSqlBinaryOpExpr, List<SQLBinaryOpExpr> sqlBinaryOpExprs, Map<Long, List<Map<String, Object>>> planResult) {
        SQLExpr left = whereSqlBinaryOpExpr.getLeft();
        SQLExpr right = whereSqlBinaryOpExpr.getRight();
        if (!(left instanceof SQLBinaryOpExpr) && !(right instanceof SQLBinaryOpExpr)) {
            sqlBinaryOpExprs.add(whereSqlBinaryOpExpr);
            return sqlBinaryOpExprs;
        }
        List<SQLBinaryOpExpr> leftSqlBinaryOpExprs = parseSQLExprWhere(plans, left, planResult);
        sqlBinaryOpExprs.addAll(leftSqlBinaryOpExprs);
        List<SQLBinaryOpExpr> rightSqlBinaryOpExprs = parseSQLExprWhere(plans, right, planResult);
        sqlBinaryOpExprs.addAll(rightSqlBinaryOpExprs);
        return sqlBinaryOpExprs;
    }

    /**
     * 转换from为正常的逻辑
     *
     * @param plans
     * @param from
     * @param planResult
     */
    private SqlTableSourceBinaryTree transFrom(List<MysqlPlan> plans, SQLTableSource from, Map<Long, List<Map<String, Object>>> planResult) {
        if (from instanceof SQLJoinTableSource) {
            SQLJoinTableSource sqlJoinTableSource = (SQLJoinTableSource) from;
            JoinType joinType = sqlJoinTableSource.getJoinType();
            SqlTableSourceBinaryTree lefts = transFrom(plans, sqlJoinTableSource.getLeft(), planResult);
            SqlTableSourceBinaryTree rights = transFrom(plans, sqlJoinTableSource.getRight(), planResult);

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
                    Asserts.assertTrue(false, "sql连表条件不支持:{}", joinType.name_lcase);
                    return null;
            }

        } else if (!(from instanceof SQLExprTableSource)) {
            SQLExprTableSource sqlExprTableSource = reExecute(from.toString(), parse -> {
                Asserts.assertTrue(CollectionUtil.isNotEmpty(parse), "解析plan为空:{}", from.toString());
                plans.addAll(parse);
                MysqlPlan fromFirstPlan = parse.get(0);
                long index = fromFirstPlan.getId();
                return new SQLExprTableSource(new MySqlCharExpr("&" + index), from.getAlias());
            });
            return pool.getOrCreateObject(sqlExprTableSource);
        } else {
            return pool.getOrCreateObject(from);
        }
    }

    /**
     * 简单sql执行计划
     */
    public static class BlockQuerySelectSqlPlan extends AbstractMysqlPlan {

        protected BlockQuerySelectSqlPlan(long id, String sql, Map<String, Object> params, Map<Long, List<Map<String, Object>>> lastPlanResult) {
            super(id, sql, params, lastPlanResult);
        }

        @Override
        public MysqlPlanResult invoke() {
            return null;
        }
    }

    /**
     * 全连接执行计划
     */
    public static class InnerJoinSqlPlan extends AbstractMysqlPlan {

        /**
         * 左边结果
         */
        private Long leftResult;

        /**
         * 右边结果
         */
        private Long rightResult;

        protected InnerJoinSqlPlan(long id, String sql, Long leftPlanId, Long rightPlanId, Map<Long, List<Map<String, Object>>> planResult) {
            super(id, sql, null, planResult);
            this.leftResult = leftPlanId;
            this.rightResult = rightPlanId;
        }

        @Override
        public MysqlPlanResult invoke() {
            return null;
        }
    }

    /**
     * 左连接执行计划
     */
    public static class LeftJoinSqlPlan extends AbstractMysqlPlan {

        /**
         * 左边结果
         */
        private Long leftResult;

        /**
         * 右边结果
         */
        private Long rightResult;

        protected LeftJoinSqlPlan(long id, String sql, Long leftPlanId, Long rightPlanId, Map<Long, List<Map<String, Object>>> planResult) {
            super(id, sql, null, planResult);
            this.leftResult = leftPlanId;
            this.rightResult = rightPlanId;
        }

        @Override
        public MysqlPlanResult invoke() {
            return null;
        }
    }


    /**
     * 右连接执行计划
     */
    public static class RightJoinSqlPlan extends AbstractMysqlPlan {

        /**
         * 左边结果
         */
        private Long leftResult;

        /**
         * 右边结果
         */
        private Long rightResult;

        protected RightJoinSqlPlan(long id, String sql, Long leftPlanId, Long rightPlanId, Map<Long, List<Map<String, Object>>> planResult) {
            super(id, sql, null, planResult);
            this.leftResult = leftPlanId;
            this.rightResult = rightPlanId;
        }

        @Override
        public MysqlPlanResult invoke() {
            return null;
        }
    }


}
