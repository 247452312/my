package indi.uhyils.protocol.mysql.pojo.request.impl;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.pojo.entity.SelectSql;
import indi.uhyils.pojo.entity.Sql;
import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.util.Asserts;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQueryRequest extends AbstractMysqlRequest {

    private String sql;

    public ComQueryRequest(MysqlHandler mysqlHandler, String sql) {
        super(mysqlHandler);
        this.sql = sql;
    }

    public ComQueryRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 4);
        this.sql = proto.get_null_str();
    }

    @Override
    public List<MysqlResponse> invoke() {
        if (StringUtils.isBlank(sql)) {
            return Collections
                .singletonList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "sql语句不能为空"));
        }

        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Sql sqlEntity = new Sql(sql, null);
        sqlEntity.parse();
        if (sqlStatement instanceof SQLSelectStatement) {
            SelectSql transformation = (SelectSql) sqlEntity.transformation();
            List<SQLSelectQueryBlock> sqlSelectQueryBlocks = transformation.blockQuerys();
        } else if (sqlStatement instanceof SQLUpdateStatement) {
        } else if (sqlStatement instanceof SQLInsertStatement) {
        } else if (sqlStatement instanceof SQLDeleteStatement) {
        } else {
            return Collections
                .singletonList(new OkResponse(getMysqlHandler(), SqlTypeEnum.NULL));
        }

        Asserts.assertTrue(false, "不识别的sql语句:{}", sql);
        return null;
    }
}
