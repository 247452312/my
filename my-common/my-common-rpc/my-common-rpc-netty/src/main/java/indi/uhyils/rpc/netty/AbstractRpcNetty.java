package indi.uhyils.rpc.netty;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.Channel;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月20日 14时14分
 */
public abstract class AbstractRpcNetty implements RpcNetty {

    /**
     * 默认的指向自身的target
     */
    private static final Object defaultTarget = new Object();

    /**
     * 超时时间
     */
    protected Long timeOut;

    /**
     * bootstrap
     */
    protected AbstractBootstrap<?, ? extends Channel> bootstrap;

    /**
     * 回调
     */
    protected RpcCallBack callback;

    protected AbstractRpcNetty() {
    }

    @Override
    public void init(Object... params) throws InterruptedException {
        this.timeOut = (Long) params[0];
        this.callback = (RpcCallBack) params[1];
    }

    public Long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 获取rpc回调方法
     *
     * @return
     */
    public RpcCallBack getRpcCallBack() {
        return callback;
    }

    public void setRpcCallback(RpcCallBack callback) {
        this.callback = callback;
    }
}
