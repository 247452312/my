package indi.uhyils.util.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLStatement;
import indi.uhyils.util.mysql.plan.MysqlPlan;
import java.util.List;

/**
 * sql解释器,每个都是单例
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 11时14分
 */
public interface Interpreter {

    /**
     * 是否可以解释,既是否是此解释器
     *
     * @param sql
     *
     * @return
     */
    boolean canParse(SQLStatement sql);


    /**
     * 将sql解释为执行计划
     *
     * @param sql
     *
     * @return
     */
    List<MysqlPlan> parse(SQLStatement sql);

}
