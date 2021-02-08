package indi.uhyils.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.enum_.RpcRequestContentEnum;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;

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
    public RpcResult invoke(RpcInvoker invoker, FilterContext invokerContext) throws RpcException, ClassNotFoundException, InterruptedException {
        addUnique(invokerContext.getRpcResult().get());
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invokerContext);
        return command.execute();
    }

    /**
     * 添加业务位唯一标示
     *
     * @param rpcData
     */
    private void addUnique(RpcData rpcData) throws InterruptedException {
        JSONArray argsMap = JSON.parseArray(rpcData.contentArray()[RpcRequestContentEnum.ARG_MAP.getLine()]);
        JSONObject o = (JSONObject) argsMap.get(0);
        DefaultRequest defaultRequest = o.toJavaObject(DefaultRequest.class);
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        try {
            defaultRequest.setIdempotentId(bean.newId());
            List<DefaultRequest> defaultRequests = Arrays.asList(defaultRequest);
            rpcData.contentArray()[RpcRequestContentEnum.ARG_MAP.getLine()] = JSON.toJSONString(defaultRequests);
        } catch (IdGenerationException e) {
            LogUtil.error(this, e);
        }
    }
}
