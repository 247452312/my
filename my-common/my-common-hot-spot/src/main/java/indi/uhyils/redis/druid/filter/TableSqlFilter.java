package indi.uhyils.redis.druid.filter;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import indi.uhyils.enums.CacheTypeEnum;
import indi.uhyils.redis.aop.HotSpotAop;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;


/**
 * 解析table的sql filter
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月11日 09时01分
 */
public class TableSqlFilter extends FilterEventAdapter {

    /**
     * 工具人
     */
    private final HotSpotAop util;

    public TableSqlFilter() {
        util = SpringUtil.getBean(HotSpotAop.class);
    }


    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
        super.statementExecuteAfter(statement, sql, result);
        if (!result) {
            return;
        }
        CacheTypeEnum cacheTypeEnum = util.getMarkThreadLocal().get();
        //如果此接口不允许缓存
        if (CacheTypeEnum.NOT_TYPE.equals(cacheTypeEnum)) {
            return;
        }
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        List<String> tableNames = new ArrayList<>();
        statementList.stream().filter(t -> !(t instanceof SQLSelectStatement)).forEach(stmt -> {
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            stmt.accept(visitor);
            Map<TableStat.Name, TableStat> tables = visitor.getTables();
            for (TableStat.Name entry : tables.keySet()) {
                String tableName = entry.getName();
                tableNames.add(tableName);
            }
        });
        if (!CollectionUtils.isEmpty(tableNames)) {
            util.doHotSpotWrite(tableNames, cacheTypeEnum);
        }

    }
}
