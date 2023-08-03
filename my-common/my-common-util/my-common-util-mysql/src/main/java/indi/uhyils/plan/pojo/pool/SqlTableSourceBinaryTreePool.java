package indi.uhyils.plan.pojo.pool;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource.JoinType;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.pool.AbstractObjectPool;
import java.util.List;

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


    public SqlTableSourceBinaryTree getOrCreateObject(SQLExprTableSource tableSource, List<SQLBinaryOpExpr> where) {
        SqlTableSourceBinaryTree orCreateObject = super.getOrCreateObject();
        MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        SQLName name = tableSource.getName();

        String owner = null;
        if (name instanceof SQLPropertyExpr) {
            SQLPropertyExpr sqlPropertyExpr = (SQLPropertyExpr) name;
            owner = sqlPropertyExpr.getOwner() != null ? sqlPropertyExpr.getOwnernName() : mysqlTcpInfo.getDatabase();
        } else if (name instanceof SQLIdentifierExpr) {
            owner = mysqlTcpInfo.getDatabase();
        }

        orCreateObject.setTableSource(new SQLExprTableSource(new SQLPropertyExpr(owner, name.getSimpleName()), tableSource.getAlias()));
        orCreateObject.setWhere(where);
        return orCreateObject;
    }

    public SqlTableSourceBinaryTree getOrCreateObject(SqlTableSourceBinaryTree leftTree, SqlTableSourceBinaryTree rightTree, SQLBinaryOpExpr condition, JoinType joinType) {
        SqlTableSourceBinaryTree orCreateObject = super.getOrCreateObject();
        orCreateObject.setLeftTree(leftTree);
        orCreateObject.setRightTree(rightTree);
        orCreateObject.setJoinType(joinType);
        orCreateObject.setCondition(condition);
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
