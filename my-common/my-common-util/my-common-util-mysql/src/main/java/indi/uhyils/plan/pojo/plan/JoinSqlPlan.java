package indi.uhyils.plan.pojo.plan;

import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年07月31日 15时35分
 */
public abstract class JoinSqlPlan extends AbstractMysqlSqlPlan {

    /**
     * 左边执行计划id
     */
    protected List<Long> leftResultPlanIds;

    /**
     * 左边结果
     */
    protected List<NodeInvokeResult> leftResults;


    /**
     * 左树
     */
    protected SqlTableSourceBinaryTree leftTree;

    /**
     * 右边执行计划id
     */
    protected List<Long> rightResultPlanIds;

    /**
     * 右边结果
     */
    protected List<NodeInvokeResult> rightResults;

    /**
     * 右树
     */
    protected SqlTableSourceBinaryTree rightTree;

    /**
     * 连接条件
     */
    protected SQLBinaryOpExpr condition;

    protected JoinSqlPlan(Map<String, String> headers, SqlTableSourceBinaryTree tree, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(null, headers, new HashMap<>());
        this.leftResultPlanIds = leftPlanId;
        this.rightResultPlanIds = rightPlanId;
        this.leftTree = tree.getLeftTree();
        this.rightTree = tree.getRightTree();
        this.condition = tree.getCondition();
    }

    /**
     * 根据子树查询结果
     *
     * @param tree
     *
     * @return
     */
    private static List<FieldInfo> findResultByTree(SqlTableSourceBinaryTree tree, List<NodeInvokeResult> results) {
        SQLPropertyExpr tableSource = tree.getTableSource();
        SQLObject parent = tableSource.getParent();
        SQLExprTableSource rightTableSource = (SQLExprTableSource) parent;
        String rightAlias = rightTableSource.getAlias();
        if (StringUtil.isEmpty(rightAlias)) {
            rightAlias = ((SQLPropertyExpr) rightTableSource.getExpr()).getName();
        }
        List<FieldInfo> result = new ArrayList<>();
        for (NodeInvokeResult rightResult : results) {
            String finalRightAlias = rightAlias;
            result.addAll(rightResult.getFieldInfos().stream().map(s -> s.copyWithNewFieldName(finalRightAlias, s.getFieldName())).collect(Collectors.toList()));
        }
        return result;
    }

    @Override
    public void complete(Map<Long, NodeInvokeResult> planArgs) {
        this.leftResults = leftResultPlanIds.stream().map(planArgs::get).collect(Collectors.toList());
        this.rightResults = rightResultPlanIds.stream().map(planArgs::get).collect(Collectors.toList());
    }

    /**
     * 整合所有的fieldInfo
     *
     * @return
     */
    protected List<FieldInfo> allFieldInfo() {
        Set<FieldInfo> result = new HashSet<>();
        result.addAll(findResultByTree(leftTree, leftResults));
        result.addAll(findResultByTree(rightTree, rightResults));
        return new ArrayList<>(result);
    }

    /**
     * 切割on连接字符
     *
     * @return
     */
    protected List<SQLBinaryOpExpr> splitCondition() {
        if (condition == null) {
            return new ArrayList<>();
        }
        return splitCondition(condition);
    }

    /**
     * 切割
     *
     * @param condition
     *
     * @return
     */
    private List<SQLBinaryOpExpr> splitCondition(SQLBinaryOpExpr condition) {
        SQLBinaryOperator operator = condition.getOperator();
        if (operator != SQLBinaryOperator.BooleanAnd && operator != SQLBinaryOperator.BooleanOr) {
            List<SQLBinaryOpExpr> sqlBinaryOpExprs = new ArrayList<>();
            sqlBinaryOpExprs.add(condition);
            return sqlBinaryOpExprs;
        }
        List<SQLBinaryOpExpr> result = new ArrayList<>();
        result.addAll(splitCondition((SQLBinaryOpExpr) condition.getLeft()));
        result.addAll(splitCondition((SQLBinaryOpExpr) condition.getRight()));

        return result;

    }

}
