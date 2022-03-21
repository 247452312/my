package indi.uhyils.protocol.mysql.plan.interpreter.query;

import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import indi.uhyils.protocol.mysql.plan.MysqlPlan;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * union解释器
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 11时17分
 */
@Component
public class UnionSelectInterpreter extends AbstractSelectInterpreter {


    @Override
    public List<MysqlPlan> doParse(SQLSelectStatement sql) {
        // todo union语句
        return new ArrayList<>();
    }

    @Override
    protected boolean doCanParse(SQLSelectStatement sql) {
        SQLSelectQuery query = sql.getSelect().getQuery();
        if (query instanceof SQLUnionQuery) {
            return true;
        }
        return false;
    }
}
