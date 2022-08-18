package indi.uhyils.plan.pojo;

import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource.JoinType;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import indi.uhyils.annotation.NotNull;

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
    private SQLTableSource tableSource;

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

    public SqlTableSourceBinaryTree(SQLTableSource tableSource) {
        this.tableSource = tableSource;
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

    public SQLTableSource getTableSource() {
        return tableSource;
    }

    public void setTableSource(SQLTableSource tableSource) {
        this.tableSource = tableSource;
    }

    @NotNull
    public Boolean isLevel() {
        return leftTree == null && rightTree == null;
    }
}
