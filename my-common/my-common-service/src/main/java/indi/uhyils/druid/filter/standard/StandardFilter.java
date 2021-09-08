package indi.uhyils.druid.filter.standard;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import indi.uhyils.pojo.entity.SelectSql;
import indi.uhyils.pojo.entity.Sql;
import java.sql.SQLException;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月07日 10时42分
 */
@Component
public class StandardFilter extends FilterEventAdapter {


    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sqlStr) throws SQLException {
        Sql sql = new Sql(sqlStr);
        sql.parse();
        Sql.SqlType type = sql.type();
        switch (type) {
            case SELECT:
                SelectSql transformation = (SelectSql) sql.transformation();
                transformation.fillDeleteFlag();
                String sqlResult = transformation.sql();
                return super.connection_prepareStatement(chain, connection, sqlResult);
            case UPDATE:
            case INSERT:
            case DELETE:
            default:
                return super.connection_prepareStatement(chain, connection, sqlStr);
        }
    }
}
