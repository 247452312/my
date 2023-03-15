package indi.uhyils.rpc.netty.spi.filter.invoker;

import indi.uhyils.rpc.exception.RpcNetException;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.core.RpcNettyConsumer;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 21时07分
 */
public class LastConsumerInvoker implements RpcInvoker {

    /**
     * netty
     */
    private RpcNettyConsumer netty;

    public LastConsumerInvoker(RpcNettyConsumer netty) {
        this.netty = netty;
    }

    @Override
    public RpcData invoke(FilterContext context) {
        RpcData request = context.getRequestData();
        LogUtil.info("请求唯一标示:{},方法:{}", request.unique().toString(), request.content().contentString());

        if (netty.sendMsg(request.toBytes())) {
            return netty.wait(request.unique());
        }
        throw new RpcNetException();
    }
}
