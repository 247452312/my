package indi.uhyils.rpc.netty;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.Channel;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月20日 14时14分
 */
public abstract class AbstractRpcNetty implements RpcNetty {

    /**
     * bootstrap
     */
    protected AbstractBootstrap<?, ? extends Channel> bootstrap;

    @Override
    public AbstractBootstrap<?, ? extends Channel> getBootstrap() {
        return bootstrap;
    }

    @Override
    public void setBootstrap(AbstractBootstrap<?, ? extends Channel> bootstrap) {
        this.bootstrap = bootstrap;
    }
}
