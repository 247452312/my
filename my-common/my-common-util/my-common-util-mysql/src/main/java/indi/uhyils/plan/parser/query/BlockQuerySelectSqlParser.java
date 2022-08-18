package indi.uhyils.plan.parser.query;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
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
import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.MySqlListExpr;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.result.MysqlPlanResult;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
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
            Asserts.throwException("查询报错,子查询类型找不到:{}", t.toString());
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * 制作执行计划
     *
     * @param result 出参
     * @param froms  目标表(多个)
     *
     * @return
     */
    @NotNull
    private List<MysqlPlan> makePlan(List<MysqlPlan> plans, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms) {
        List<MysqlPlan> resultPlan = new ArrayList<>();
        if (froms.isLevel()) {
            long id = 0;
            if (CollectionUtil.isNotEmpty(plans)) {
                id = plans.get(plans.size() - 1).getId();
            }
            MysqlPlan mysqlPlan = new BlockQuerySelectSqlPlan(id + 1, froms.toString(), null);
            resultPlan.add(mysqlPlan);
            return resultPlan;
        } else {
            JoinType joinType = froms.getJoinType();

            switch (joinType) {
                case INNER_JOIN:
                    return makeInnerJoin(plans, result, froms);
                case LEFT_OUTER_JOIN:
                    return makeLeftJoin(plans, result, froms);
                case RIGHT_OUTER_JOIN:
                    return makeRightJoin(plans, result, froms);
                default:
                    Asserts.throwException("无指定连表方案");
                    return null;
            }
        }
    }

    @NotNull
    private List<MysqlPlan> makeRightJoin(List<MysqlPlan> plans, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms) {
        List<MysqlPlan> rightPlan = makePlan(plans, result, froms.getRightTree());
        plans.addAll(rightPlan);
        List<MysqlPlan> leftPlan = makePlan(plans, result, froms.getLeftTree());
        plans.addAll(leftPlan);
        long id = plans.get(plans.size() - 1).getId();
        long leftId = leftPlan.get(leftPlan.size() - 1).getId();
        long right = rightPlan.get(rightPlan.size() - 1).getId();
        MysqlPlan sqlPlan = new RightJoinSqlPlan(id + 1, null, leftId, right);
        plans.add(sqlPlan);
        return plans;
    }

    @NotNull
    private List<MysqlPlan> makeLeftJoin(List<MysqlPlan> plans, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms) {
        List<MysqlPlan> leftPlan = makePlan(plans, result, froms.getLeftTree());
        plans.addAll(leftPlan);
        List<MysqlPlan> rightPlan = makePlan(plans, result, froms.getRightTree());
        plans.addAll(rightPlan);
        long id = plans.get(plans.size() - 1).getId();
        long leftId = leftPlan.get(leftPlan.size() - 1).getId();
        long right = rightPlan.get(rightPlan.size() - 1).getId();
        MysqlPlan sqlPlan = new LeftJoinSqlPlan(id + 1, null, leftId, right);
        plans.add(sqlPlan);
        return plans;
    }

    @NotNull
    private List<MysqlPlan> makeInnerJoin(List<MysqlPlan> plans, List<SQLSelectItem> result, SqlTableSourceBinaryTree froms) {
        List<MysqlPlan> leftPlan = makePlan(plans, result, froms.getLeftTree());
        plans.addAll(leftPlan);
        List<MysqlPlan> rightPlan = makePlan(plans, result, froms.getRightTree());
        plans.addAll(rightPlan);
        long id = plans.get(plans.size() - 1).getId();
        long leftId = leftPlan.get(leftPlan.size() - 1).getId();
        long right = rightPlan.get(rightPlan.size() - 1).getId();
        MysqlPlan sqlPlan = new InnerJoinSqlPlan(id + 1, null, leftId, right);
        plans.add(sqlPlan);
        return plans;
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
        Asserts.throwException("sql_where解析错误,没有找到解析类型:{}", where);
        return null;
    }

    private List<SQLBinaryOpExpr> parseSqlBinaryOpExprWhere(List<MysqlPlan> plans, SQLBinaryOpExpr whereSqlBinaryOpExpr, List<SQLBinaryOpExpr> sqlBinaryOpExprs) {
        SQLExpr left = whereSqlBinaryOpExpr.getLeft();
        SQLExpr right = whereSqlBinaryOpExpr.getRight();
        if (whereSqlBinaryOpExpr.getOperator().isRelational()) {
            if (right instanceof SQLValuableExpr) {
                sqlBinaryOpExprs.add(whereSqlBinaryOpExpr);
                return sqlBinaryOpExprs;
            }
            if (right instanceof SQLQueryExpr) {
                final int index = plans.size() - 1;
                reExecute(right.toString(), (Consumer<List<MysqlPlan>>) plans::addAll);
                SQLBinaryOpExpr sqlBinaryOpExpr = new SQLBinaryOpExpr(left, whereSqlBinaryOpExpr.getOperator(), new MySqlCharExpr("&" + index));
                sqlBinaryOpExprs.add(sqlBinaryOpExpr);
                return sqlBinaryOpExprs;
            }
            Asserts.throwException("未知的where条件类型:{},条件内容:{}", right.getClass().getName(), right.toString());

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
    private SqlTableSourceBinaryTree transFrom(List<MysqlPlan> plans, SQLTableSource from) {
        if (from instanceof SQLJoinTableSource) {
            SQLJoinTableSource sqlJoinTableSource = (SQLJoinTableSource) from;
            JoinType joinType = sqlJoinTableSource.getJoinType();
            SqlTableSourceBinaryTree lefts = transFrom(plans, sqlJoinTableSource.getLeft());
            SqlTableSourceBinaryTree rights = transFrom(plans, sqlJoinTableSource.getRight());

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
     * 解析select语句
     *
     * @param select
     *
     * @return
     */
    private List<MysqlPlan> parseSelect(SQLSelect select) {
        // 1. 处理where中的子查询
        // 2.处理from后需要查询的条件
        // 3.selectList 查询字段的子查询
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        final ArrayList<MysqlPlan> plans = new ArrayList<>();
        final SqlTableSourceBinaryTree froms = transFrom(plans, query.getFrom());

        final List<SQLBinaryOpExpr> where = parseSQLExprWhere(plans, query.getWhere());
        final List<SQLSelectItem> selectList = parseSelectList(plans, query.getSelectList());

        return makePlan(plans, selectList, froms);
    }

    /**
     * 简单sql执行计划
     */
    public static class BlockQuerySelectSqlPlan extends AbstractMysqlSqlPlan {

        protected BlockQuerySelectSqlPlan(long id, String sql, Map<String, Object> params) {
            super(id, sql, params);
        }

        @Override
        public MysqlPlanResult invoke() {
            return null;
        }
    }

    /**
     * 全连接执行计划
     */
    public static class InnerJoinSqlPlan extends AbstractMysqlSqlPlan {

        /**
         * 左边结果
         */
        private Long leftResult;

        /**
         * 右边结果
         */
        private Long rightResult;

        protected InnerJoinSqlPlan(long id, String sql, Long leftPlanId, Long rightPlanId) {
            super(id, sql, null);
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
    public static class LeftJoinSqlPlan extends AbstractMysqlSqlPlan {

        /**
         * 左边结果
         */
        private Long leftResult;

        /**
         * 右边结果
         */
        private Long rightResult;

        protected LeftJoinSqlPlan(long id, String sql, Long leftPlanId, Long rightPlanId) {
            super(id, sql, null);
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
    public static class RightJoinSqlPlan extends AbstractMysqlSqlPlan {

        /**
         * 左边结果
         */
        private Long leftResult;

        /**
         * 右边结果
         */
        private Long rightResult;

        protected RightJoinSqlPlan(long id, String sql, Long leftPlanId, Long rightPlanId) {
            super(id, sql, null);
            this.leftResult = leftPlanId;
            this.rightResult = rightPlanId;
        }

        @Override
        public MysqlPlanResult invoke() {
            return null;
        }
    }

    @Override
    protected boolean doCanParse(SQLSelectStatement sql) {
        SQLSelectQuery query = sql.getSelect().getQuery();
        return query instanceof MySqlSelectQueryBlock;
    }

    @Override
    protected List<MysqlPlan> doParse(SQLSelectStatement sql) {
        return parseSelect(sql.getSelect());
    }


}
