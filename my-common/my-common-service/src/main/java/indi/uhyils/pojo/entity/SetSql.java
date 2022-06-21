package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.statement.SQLAssignItem;
import com.alibaba.druid.sql.ast.statement.SQLSetStatement;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 08时27分
 */
public class SetSql extends Sql {


    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private final SQLSetStatement sqlStatement;

    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public SetSql(Sql sql) {
        super(sql.sql, sql.ignoreTables);
        Asserts.assertTrue(SqlType.UPDATE.equals(sql.sqlType), "类型错误");
        this.sqlStatement = (SQLSetStatement) sql.sqlStatement;
    }

    @Override
    public String sql() {
        super.sql();
        this.sql = sqlStatement.toString();
        LogUtil.info("change++++\n" + sql);
        return this.sql;
    }

    public Map<String, Object> getSetMap() {
        Map<String, Object> result = new HashMap<>(sqlStatement.getItems().size());
        for (SQLAssignItem item : sqlStatement.getItems()) {
            result.put(item.getTarget().toString(), item.getValue());
        }
        return result;
    }


}
