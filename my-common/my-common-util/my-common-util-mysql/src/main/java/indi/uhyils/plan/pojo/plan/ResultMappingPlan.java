package indi.uhyils.plan.pojo.plan;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.MysqlPlan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结果映射执行计划
 * <p>
 * 需要将子查询,mysql方法执行结果等整合到结果中
 * <p>
 * 1. 多个字段映射成少字段
 * 例如
 * <p>
 * sql: select id from user where id in (1,2)
 * 第一步,查出整个表
 * __________________________
 * | id   | name    | age   |
 * |------------------------|
 * | 1    | 姓名     | 18    |
 * | 2    | 姓名2    | 16    |
 * --------------------------
 * <p>
 * 第二步, 字段映射,结果为
 * ________
 * | id   |
 * |------|
 * | 1    |
 * | 2    |
 * --------
 * 2. 子查询,方法等结果映射为此处的结果
 * sql: select a.id,(select count(*) from user b where b.id in (1,2)) as size from user a where a.id in (1,2)
 *
 * 第一步,查出整个表
 * __________________________
 * | id   | name    | age   |
 * |------------------------|
 * | 1    | 姓名     | 18    |
 * | 2    | 姓名2    | 16    |
 * --------------------------
 * 第二步,将子查询 (select count(*) from user b where b.id in (1,2))结果查询出来
 * ______________
 * | count(*)   |
 * |------------|
 * | 2          |
 * --------------
 *
 * 将两个表的结果组合
 *
 * _______________
 * | id   | size |
 * |-------------|
 * | 1    | 2    |
 * | 2    | 2    |
 * ---------------
 *
 * 注: 为什么没有 select a.id,(select b.`name` from user b where b.id  = a.id ) as name from user a where a.id in (1,2) 这种复杂子查询? 因为这种sql语句在解析为执行计划时会优化为 SELECT a.id, b.age FROM `user` a INNER JOIN `user` b ON a.id = b.id WHERE a.id IN ( 1, 2 )
 *
 *
 *
 *
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时57分
 */
public abstract class ResultMappingPlan extends AbstractMysqlSqlPlan {

    /**
     * 要映射成的几个字段
     */
    protected final List<SQLSelectItem> selectList;

    /**
     * 主sql执行计划
     */
    protected final MysqlPlan lastMainPlan;

    protected ResultMappingPlan(Map<String, String> headers, MysqlPlan lastMainPlan, List<SQLSelectItem> selectList) {
        super(null, headers, new HashMap<>());
        this.selectList = selectList;
        this.lastMainPlan = lastMainPlan;
    }
}
