package indi.uhyils.elegant.assembly.rpc;

import indi.uhyils.elegant.AbstractElegantHandler;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ProviderFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月03日 19时05分
 */
@RpcSpi
public class ElegantRpcFilter extends AbstractElegantHandler implements ProviderFilter {


    /**
     * 是否在线
     */
    private final AtomicBoolean online = new AtomicBoolean(Boolean.TRUE);

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws InterruptedException {

        if (Boolean.FALSE.equals(online.get())) {
            // todo 这里是否可以报错来代替return
            return null;
        }
        if (Boolean.FALSE.equals(canPublish())) {
            return null;
        }
        newRequest();
        try {
            return invoker.invoke(invokerContext);
        } finally {
            requestOver();
        }
    }

    @Override
    public Boolean isOnline() {
        return online.get();
    }

    @Override
    public void close() {
        doShutdown();
    }

    @Override
    protected void doShutdown() {
        online.set(Boolean.FALSE);
    }
}
