package indi.uhyils.protocol.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
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

    /**
     * 除了常规之外的其他sql
     */
    private final List<AbstractSelectInterpreter> selectInterpreters;

    public BlockQuerySelectInterpreter(List<AbstractSelectInterpreter> selectInterpreters) {
        selectInterpreters.removeIf(next -> next instanceof BlockQuerySelectInterpreter);
        this.selectInterpreters = selectInterpreters;
    }

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
        // from
        SQLTableSource from = query.getFrom();
        // where
        SQLExpr where = query.getWhere();
        // list
        List<SQLSelectItem> selectList = query.getSelectList();
        // 如果 from 不是常规from 而是其他,则转换from为
        if (!(from instanceof SQLExprTableSource)) {
            for (AbstractSelectInterpreter selectInterpreter : selectInterpreters) {
            }
        }
        return new ArrayList<>();
    }
}
