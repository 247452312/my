package indi.uhyils.plan.pojo.plan;

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
    protected Long leftResultPlanId;

    /**
     * 左边结果
     */
    protected NodeInvokeResult leftResult;


    /**
     * 左树
     */
    protected SqlTableSourceBinaryTree leftTree;

    /**
     * 右边执行计划id
     */
    protected Long rightResultPlanId;

    /**
     * 右边结果
     */
    protected NodeInvokeResult rightResult;

    /**
     * 右树
     */
    protected SqlTableSourceBinaryTree rightTree;

    /**
     * 连接条件
     */
    protected SQLBinaryOpExpr condition;

    protected JoinSqlPlan(Map<String, String> headers, SqlTableSourceBinaryTree tree, Long leftPlanId, Long rightPlanId) {
        super(null, headers, new HashMap<>());
        this.leftResultPlanId = leftPlanId;
        this.rightResultPlanId = rightPlanId;
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
    private static List<FieldInfo> findResultByTree(SqlTableSourceBinaryTree tree, NodeInvokeResult results) {
        SQLExprTableSource tableSource = tree.getTableSource();
        String rightAlias = tableSource.getAlias();
        if (StringUtil.isEmpty(rightAlias)) {
            rightAlias = ((SQLPropertyExpr) tableSource.getExpr()).getName();
        }
        String finalRightAlias = rightAlias;
        return results.getFieldInfos().stream().map(s -> s.copyWithNewFieldName(finalRightAlias, s.getFieldName())).collect(Collectors.toList());
    }

    @Override
    public void complete(Map<Long, NodeInvokeResult> planArgs) {
        this.leftResult = planArgs.get(leftResultPlanId);
        this.rightResult = planArgs.get(rightResultPlanId);
    }

    /**
     * 整合所有的fieldInfo
     *
     * @return
     */
    protected List<FieldInfo> allFieldInfo() {
        Set<FieldInfo> result = new HashSet<>();
        result.addAll(findResultByTree(leftTree, leftResult));
        result.addAll(findResultByTree(rightTree, rightResult));
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
     * 检查两行是否可以合并
     *
     * @param left
     * @param right
     * @param on
     *
     * @return
     */
    protected boolean checkMerge(Map<String, Object> left, Map<String, Object> right, List<SQLBinaryOpExpr> on) {
        for (SQLBinaryOpExpr sqlBinaryOpExpr : on) {
            int i = 1;
        }
        return false;
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
