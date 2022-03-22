package indi.uhyils.util.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLAssignItem;
import com.alibaba.druid.sql.ast.statement.SQLSetStatement;
import indi.uhyils.util.mysql.plan.MysqlPlan;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 14时02分
 */
@Component
public class SetInterpreter implements Interpreter {

    @Override
    public boolean canParse(SQLStatement sql) {
        if (sql instanceof SQLSetStatement) {
            return true;
        }
        return false;
    }

    @Override
    public List<MysqlPlan> parse(SQLStatement sql) {
        SQLSetStatement setSql = (SQLSetStatement) sql;
        List<SQLAssignItem> items = setSql.getItems();
        for (SQLAssignItem sqlAssignItem : items) {
            // 要被改的变量名称
            String target = sqlAssignItem.getTarget().toString();
            // 改成什么
            String value = sqlAssignItem.getValue().toString();
        }
        // todo 暂时忽略set语句
        return new ArrayList<>();
    }
}
