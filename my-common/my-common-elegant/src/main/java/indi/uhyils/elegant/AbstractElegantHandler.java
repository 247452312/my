package indi.uhyils.elegant;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 优雅上下线组件注册模板
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月03日 08时25分
 */
public abstract class AbstractElegantHandler implements ElegantHandler {

    /**
     * 是否可以对外发布服务
     */
    private final AtomicBoolean canPublish = new AtomicBoolean(Boolean.FALSE);

    /**
     * 正在处理的业务数量
     */
    private final AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 是否可以对外发布服务
     *
     * @return
     */
    protected Boolean canPublish() {
        return this.canPublish.get();
    }


    @Override
    public void allowToPublish() {
        canPublish.set(Boolean.TRUE);
    }

    @Override
    public void notAllowToPublish() {
        canPublish.set(Boolean.FALSE);
    }

    @Override
    public void shutdown() {
        notAllowToPublish();
        doShutdown();
    }

    @Override
    public Boolean isBusy() {
        return Objects.equals(onlineCount.get(), 1);
    }

    /**
     * 新请求
     */
    protected void newRequest() {
        onlineCount.getAndIncrement();
    }

    /**
     * 请求结束
     */
    protected void requestOver() {
        onlineCount.getAndDecrement();
    }


    /**
     * 关闭实际上要干的活
     */
    protected abstract void doShutdown();
}
