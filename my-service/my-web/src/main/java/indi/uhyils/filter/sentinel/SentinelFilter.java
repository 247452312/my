package indi.uhyils.filter.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exchange.enums.RpcRequestContentEnum;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月29日 09时10分
 */
@RpcSpi(order = Integer.MIN_VALUE)
public class SentinelFilter implements ConsumerFilter {

    private static void initFlowQpsRule(String key, Double count, Integer grade, String limitApp) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(key);
        // set limit qps to 20
        rule.setCount(count);
        // rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setGrade(grade);
        rule.setLimitApp(limitApp);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws InterruptedException {
        addUnique(invokerContext.getRequestData());
        try (Entry entry = SphU.entry("1")) {
            return invoker.invoke(invokerContext);
        } catch (BlockException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 添加业务位唯一标示
     *
     * @param rpcData
     */
    private void addUnique(RpcData rpcData) {
        ArrayList list = JSON.parseObject(rpcData.content().getLine(RpcRequestContentEnum.ARG_MAP.getLine()), ArrayList.class, Feature.SupportAutoType);
        DefaultCQE defaultRequest = (DefaultCQE) list.get(0);
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        defaultRequest.setUnique(bean.newId());
        List<DefaultCQE> defaultRequests = Arrays.asList(defaultRequest);
        rpcData.content().contentArray()[RpcRequestContentEnum.ARG_MAP.getLine()] = JSON.toJSONString(defaultRequests);

    }

}
