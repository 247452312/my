package indi.uhyils.util;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月20日 09时09分
 */
public final class SqlUtil {

    private SqlUtil() {
        throw new RuntimeException("工具类,不允许实例化");
    }

    /**
     * 转换query中的子查询为连表查询
     *
     * @param query
     */
    public static void transSelectList(SQLSelectQuery query) {
        if (query instanceof SQLSelectQueryBlock) {
            SQLSelectQueryBlock blockQuery = (SQLSelectQueryBlock) query;
            List<SQLSelectItem> selectList = blockQuery.getSelectList();
            for (SQLSelectItem sqlSelectItem : selectList) {
                //todo 等待补全
            }
        }
    }

}
