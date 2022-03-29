package indi.uhyils.parser;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowStatement;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 14时11分
 */
@Component
public class ShowSqlParser implements SqlParser {

    @Override
    public boolean canParse(SQLStatement sql) {
        if (sql instanceof MySqlShowStatement) {
            return true;
        }
        return false;
    }

    @Override
    public List<MysqlPlan> parse(SQLStatement sql) {
        // todo show命令解析
        LogUtil.info("show:{}", sql.toLowerCaseString());
        return new ArrayList<>();
    }

}
