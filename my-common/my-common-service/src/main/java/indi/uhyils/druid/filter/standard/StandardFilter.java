package indi.uhyils.druid.filter.standard;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import indi.uhyils.context.UserContext;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.InsertSql;
import indi.uhyils.pojo.entity.SelectSql;
import indi.uhyils.pojo.entity.Sql;
import indi.uhyils.pojo.entity.UpdateSql;
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
        UserDTO userDTO = UserContext.doGet();
        Long userId;
        if (userDTO != null) {
            userId = userDTO.getId();
        } else {
            userId = 0L;
        }
        switch (type) {
            case SELECT:
                SelectSql transformation = (SelectSql) sql.transformation();
                transformation.fillDeleteFlag();
                return super.connection_prepareStatement(chain, connection, transformation.sql());
            case UPDATE:
                UpdateSql updateSql = (UpdateSql) sql.transformation();
                updateSql.fillDeleteFlag();
                updateSql.addDateSet("update_date", System.currentTimeMillis());
                updateSql.addLongSet("update_user", userId);
                return super.connection_prepareStatement(chain, connection, updateSql.sql());
            case INSERT:
                InsertSql insertSql = (InsertSql) sql.transformation();
                insertSql.fillDeleteFlag();
                long time = System.currentTimeMillis();
                insertSql.addDateItem("update_date", time);
                insertSql.addLongItem("update_user", userId);
                insertSql.addDateItem("create_date", time);
                insertSql.addLongItem("create_user", userId);
                return super.connection_prepareStatement(chain, connection, insertSql.sql());
            case DELETE:
            default:
                return super.connection_prepareStatement(chain, connection, sqlStr);
        }
    }
}
