package indi.uhyils.plan.pojo;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.enums.MysqlMethodEnum;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月01日 11时50分
 */
public class MySQLSelectItem extends SQLSelectItem {

    /**
     * 原始selectItem
     */
    private SQLSelectItem selectItem;

    /**
     * 对应方法名称
     */
    private MysqlMethodEnum methodEnum;

    public MySQLSelectItem(SQLExpr expr, String alias, SQLSelectItem selectItem) {
        super(expr, alias);
        this.selectItem = selectItem;
    }

    public MySQLSelectItem(SQLExpr expr, String alias, SQLSelectItem selectItem, MysqlMethodEnum methodEnum) {
        this(expr, alias, selectItem);
        this.methodEnum = methodEnum;
    }

    /**
     * 是否是方法对应的item
     *
     * @return
     */
    public Boolean isMethodItem() {
        return methodEnum != null;
    }

    /**
     * 获取对应方法类型
     *
     * @return
     */
    public MysqlMethodEnum method() {
        return methodEnum;
    }


}
