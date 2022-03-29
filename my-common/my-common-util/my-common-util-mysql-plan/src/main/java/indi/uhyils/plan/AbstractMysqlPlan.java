package indi.uhyils.plan;

import indi.uhyils.Placeholder;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 08时49分
 */
public abstract class AbstractMysqlPlan implements MysqlPlan {

    /**
     * 执行计划id
     */
    protected final long id;

    /**
     * 执行计划原始sql
     */
    protected final String sql;

    /**
     * 执行计划参数
     */
    protected final Map<String, Object> params;

    protected AbstractMysqlPlan(long id, String sql, Map<String, Object> params) {
        this.id = id;
        this.sql = sql;
        this.params = params;
    }

    @Override
    public void complete(Map<Long, List<Map<String, Object>>> planArgs) {
        params.forEach((k, v) -> {
            // 如果是占位符
            if (v instanceof Placeholder) {
                Placeholder placeholder = (Placeholder) v;
                List<Map<String, Object>> maps = planArgs.get(placeholder.getId());
                Asserts.assertTrue(maps != null, "占位符对应的参数不存在");
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

    @Override
    public long getId() {
        return id;
    }
}
