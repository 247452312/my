package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ConsumerFilterDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.FilterRuleUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 消费过滤表(ConsumerFilter)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分03秒
 */
public class ConsumerFilter extends AbstractDoEntity<ConsumerFilterDO> {

    /**
     * 规则名称
     */
    private String filterRuleStr;

    /**
     * 规则涉及字段
     */
    private List<String> filterColNames;

    @Default
    public ConsumerFilter(ConsumerFilterDO data) {
        super(data);
    }

    public ConsumerFilter(Long id) {
        super(id, new ConsumerFilterDO());
    }

    public ConsumerFilter(Long id, ConsumerFilterRepository rep) {
        super(id, new ConsumerFilterDO());
        completion(rep);
    }

    public ConsumerFilter(Identifier id) {
        super(id, new ConsumerFilterDO());
    }

    /**
     * 获取过滤器指定的字段名称
     *
     * @return
     */
    public List<String> toColList() {
        if (this.filterColNames == null) {
            ConsumerFilterDO consumerFilterDO = toData();
            String rule = consumerFilterDO.getRule();
            String[] split = rule.split("->");
            Asserts.assertTrue(split.length == 2, "规则不符合规则,rule:{}", rule);
            String colNameStr = split[0];
            String[] colNames = colNameStr.split(",");
            List<String> list = Arrays.asList(colNames);
            this.filterColNames = list;
            return list;
        }
        return this.filterColNames;

    }

    /**
     * 执行filter
     *
     * @param filterColValues
     *
     * @return
     */
    public Boolean makeFilter(Map<String, Object> filterColValues) {
        if (this.filterRuleStr == null) {
            String rule = toData().getRule();
            // rule字段为 名称->规则
            String[] split = rule.split("->");
            Asserts.assertTrue(split.length == 2, "规则不符合规则,rule:{}", rule);
            this.filterRuleStr = split[1];
        }
        return FilterRuleUtil.matchRules(filterRuleStr, filterColValues);
    }
}
