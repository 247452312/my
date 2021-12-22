package indi.uhyils.protocol.mysql.sql;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.util.SqlUtil;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月20日 08时26分
 */
public class SqlDruidTest {

    @Test
    public void doParse() {
        String sql = "select * from (select a.barcode,(select b.sample_no from biz_lis_request_information b where b.barcode = a.barcode limit 1) as sample_no,blr.result_type from biz_lis_sample a left join biz_lis_result blr on a.sample_no = blr.sample_no where a.inpatient_id in ( select c.inpatient_id from biz_lis_request_information c where c.card_id = '321231123412344321')) e limit 10,10";
        SQLSelectStatement sqlStatement = (SQLSelectStatement) new MySqlStatementParser(sql).parseStatement();

        SQLSelectQuery query = sqlStatement.getSelect().getQuery();
        SqlUtil.transSelectList(query);
        if (query instanceof SQLSelectQueryBlock) {
            SQLSelectQueryBlock blockQuery = (SQLSelectQueryBlock) query;
            SQLTableSource from = blockQuery.getFrom();

            SQLExpr where = blockQuery.getWhere();

        }

    }

}
