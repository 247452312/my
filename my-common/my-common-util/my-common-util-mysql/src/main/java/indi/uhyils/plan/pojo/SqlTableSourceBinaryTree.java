package indi.uhyils.plan.pojo;

import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource.JoinType;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import indi.uhyils.annotation.NotNull;
import java.util.List;

/**
 * sql中 table的连接树
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月31日 13时41分
 */
public class SqlTableSourceBinaryTree {

    /**
     * 节点数据本身
     */
    private SQLPropertyExpr tableSource;

    /**
     * 条件
     */
    private List<SQLBinaryOpExpr> where;

    /**
     * 左树
     */
    private SqlTableSourceBinaryTree leftTree;

    /**
     * 右树
     */
    private SqlTableSourceBinaryTree rightTree;

    /**
     * 连接方式
     */
    private JoinType joinType;

    public SqlTableSourceBinaryTree(SQLPropertyExpr tableSource, List<SQLBinaryOpExpr> where) {
        this.tableSource = tableSource;
        this.where = where;
    }

    public SqlTableSourceBinaryTree(SqlTableSourceBinaryTree leftTree, SqlTableSourceBinaryTree rightTree, JoinType joinType) {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
        this.joinType = joinType;
    }

    public SqlTableSourceBinaryTree() {
    }

    public SqlTableSourceBinaryTree getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(SqlTableSourceBinaryTree leftTree) {
        this.leftTree = leftTree;
    }

    public SqlTableSourceBinaryTree getRightTree() {
        return rightTree;
    }

    public void setRightTree(SqlTableSourceBinaryTree rightTree) {
        this.rightTree = rightTree;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public SQLPropertyExpr getTableSource() {
        return tableSource;
    }

    public void setTableSource(SQLPropertyExpr tableSource) {
        this.tableSource = tableSource;
    }

    public List<SQLBinaryOpExpr> getWhere() {
        return where;
    }

    public void setWhere(List<SQLBinaryOpExpr> where) {
        this.where = where;
    }

    @NotNull
    public Boolean isLevel() {
        return leftTree == null && rightTree == null;
    }
}
