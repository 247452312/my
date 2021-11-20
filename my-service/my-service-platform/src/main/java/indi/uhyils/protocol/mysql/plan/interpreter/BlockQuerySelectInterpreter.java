package indi.uhyils.protocol.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import indi.uhyils.protocol.mysql.plan.MysqlPlan;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * 常规查询语句解析类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 14时18分
 */
@Component
public class BlockQuerySelectInterpreter extends AbstractSelectInterpreter {

    @Override
    protected boolean doCanParse(SQLSelectStatement sql) {
        SQLSelectQuery query = sql.getSelect().getQuery();
        if (query instanceof MySqlSelectQueryBlock) {
            return true;
        }
        return false;
    }

    @Override
    protected List<MysqlPlan> doParse(SQLSelectStatement sql) {
        // todo 解析常规查询语句
        SQLSelect select = sql.getSelect();
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        SQLTableSource from = query.getFrom();
        return new ArrayList<>();
    }
}
