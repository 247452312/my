package indi.uhyils.protocol.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSetStatement;
import indi.uhyils.protocol.mysql.plan.MysqlPlan;
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
        // todo 暂时忽略set语句
        return new ArrayList<>();
    }
}
