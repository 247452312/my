package indi.uhyils.parser;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLAssignItem;
import com.alibaba.druid.sql.ast.statement.SQLSetStatement;
import indi.uhyils.plan.MysqlPlan;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 08时54分
 */
@Component
public class SetSqlParser implements SqlParser {

    @Override
    public boolean canParse(SQLStatement sql) {
        // 检查sql是不是set语句
        if (sql instanceof SQLSetStatement) {
            return true;
        }
        return false;
    }

    @Override
    public List<MysqlPlan> parse(SQLStatement sql) {
        // 解析set语句
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
