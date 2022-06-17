package indi.uhyils.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exception.RpcException;
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
 * 熔断器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月19日 15时43分
 */
@RpcSpi(order = Integer.MIN_VALUE)
public class HystrixFilter implements ConsumerFilter {

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws RpcException, ClassNotFoundException, InterruptedException {
        addUnique(invokerContext.getRequestData());
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invokerContext);
        return command.execute();
    }

    /**
     * 添加业务位唯一标示
     *
     * @param rpcData
     */
    private void addUnique(RpcData rpcData) throws InterruptedException {
        ArrayList list = JSON.parseObject(rpcData.content().getLine(RpcRequestContentEnum.ARG_MAP.getLine()), ArrayList.class, Feature.SupportAutoType);
        DefaultCQE defaultRequest = (DefaultCQE) list.get(0);
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        defaultRequest.setUnique(bean.newId());
        List<DefaultCQE> defaultRequests = Arrays.asList(defaultRequest);
        rpcData.content().contentArray()[RpcRequestContentEnum.ARG_MAP.getLine()] = JSON.toJSONString(defaultRequests);

    }
}
