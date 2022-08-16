package indi.uhyils.plan.pojo.pool;

import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource.JoinType;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.pool.AbstractObjectPool;

/**
 * sql实例池
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月31日 13时57分
 */
public class SqlTableSourceBinaryTreePool extends AbstractObjectPool<SqlTableSourceBinaryTree> {

    /**
     * 默认实例长度
     */
    private static final Integer DEFAULT_SIZE = 1000;


    public SqlTableSourceBinaryTreePool(Integer size) {
        super(size, SqlTableSourceBinaryTree.class);
    }


    public SqlTableSourceBinaryTree getOrCreateObject(SQLTableSource tableSource) {
        SqlTableSourceBinaryTree orCreateObject = super.getOrCreateObject();
        orCreateObject.setTableSource(tableSource);
        return orCreateObject;
    }

    public SqlTableSourceBinaryTree getOrCreateObject(SqlTableSourceBinaryTree leftTree, SqlTableSourceBinaryTree rightTree, JoinType joinType) {
        SqlTableSourceBinaryTree orCreateObject = super.getOrCreateObject();
        orCreateObject.setLeftTree(leftTree);
        orCreateObject.setRightTree(rightTree);
        orCreateObject.setJoinType(joinType);
        return orCreateObject;
    }

    @Override
    protected void emptyObj(SqlTableSourceBinaryTree sqlTableSourceBinaryTree) {
        sqlTableSourceBinaryTree.setLeftTree(null);
        sqlTableSourceBinaryTree.setRightTree(null);
        sqlTableSourceBinaryTree.setJoinType(null);
        sqlTableSourceBinaryTree.setTableSource(null);
    }
}
