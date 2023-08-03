package indi.uhyils.plan;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.config.MysqlPlanConfig;
import indi.uhyils.plan.enums.MysqlPlanTypeEnum;
import indi.uhyils.plan.pojo.Placeholder;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.SpringUtil;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * sql执行计划的抽象模板
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 08时49分
 */
public abstract class AbstractMysqlSqlPlan implements MysqlSqlPlan {


    /**
     * 执行计划原始sql
     */
    protected final String sql;

    /**
     * 执行计划参数 key->参数名称 value->占位符(替换之后为实际的参数值)
     */
    protected final Map<String, Object> params;

    /**
     * 此plan唯一标示
     */
    private final long id;

    /**
     * 请求头
     */
    protected Map<String, String> headers;

    /**
     * 过去执行的所有结果
     */
    protected Map<Long, NodeInvokeResult> lastAllPlanResult;

    /**
     * 最后一次执行的结果
     */
    protected NodeInvokeResult lastNodeInvokeResult;

    /**
     * 配置
     */
    protected MysqlPlanConfig config;

    protected AbstractMysqlSqlPlan(String sql, Map<String, String> headers, Map<String, Object> params) {
        this.sql = sql;
        this.params = params;
        this.headers = headers;
        this.id = SpringUtil.getBean(IdUtil.class).newId();
        this.config = SpringUtil.getBean(MysqlPlanConfig.class);
    }

    protected AbstractMysqlSqlPlan(Long id, String sql, Map<String, String> headers, Map<String, Object> params) {
        this.sql = sql;
        this.params = params;
        this.headers = headers;
        this.id = id;
        this.config = SpringUtil.getBean(MysqlPlanConfig.class);
    }


    @Override
    public void complete(Map<Long, NodeInvokeResult> planArgs) {
        // 填充占位符
        completePlaceholder(planArgs);
        planArgs.keySet().stream().max(Long::compareTo).ifPresent(aLong -> this.lastNodeInvokeResult = planArgs.get(aLong));
        this.lastAllPlanResult = planArgs;
    }

    @Override
    public MysqlPlanTypeEnum type() {
        return MysqlPlanTypeEnum.EXECUTE_SQL;
    }

    @Override
    public long getId() {
        return id;
    }

    public Map<String, Object> toParams() {
        return params;
    }

    public Map<String, String> toHeaders() {
        return headers;
    }

    /**
     * 填充占位符
     *
     * @param planArgs
     */
    protected void completePlaceholder(Map<Long, NodeInvokeResult> planArgs) {
        params.forEach((k, v) -> {
            // 如果是占位符
            if (v instanceof Placeholder) {
                Placeholder placeholder = (Placeholder) v;
                NodeInvokeResult nodeInvokeResult = planArgs.get(placeholder.getId());
                Asserts.assertTrue(nodeInvokeResult != null, "占位符对应的参数不存在");
                List<Map<String, Object>> maps = nodeInvokeResult.getResult();

                String name = placeholder.getName();
                List<Object> collect = maps.stream().map(t -> t.get(name)).filter(Objects::nonNull).collect(Collectors.toList());
                if (collect.size() == 1) {
                    Object o = collect.get(0);
                    params.put(k, o);
                } else {
                    params.put(k, collect);
                }
            }
        });
    }
}
