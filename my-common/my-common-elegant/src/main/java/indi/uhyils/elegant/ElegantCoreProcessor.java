package indi.uhyils.elegant;

import indi.uhyils.MyExecutorWrapper;
import indi.uhyils.rpc.netty.spi.filter.RpcFilter;
import indi.uhyils.rpc.registry.manager.MyRpcRegistryManagerFactory;
import indi.uhyils.rpc.spi.RpcSpiManager;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * 优雅上下线核心处理器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月02日 08时54分
 */
@Component
public class ElegantCoreProcessor implements InitializingBean, ApplicationListener<ContextClosedEvent> {

    private static ExecutorService es = MyExecutorWrapper.createByThreadPoolExecutor(new ThreadPoolExecutor(2, 100, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10)));

    private List<ElegantHandler> handlers;


    @Override
    public void afterPropertiesSet() throws Exception {

        LogUtil.info("应用优雅上下线核心处理器开始行动!");
        this.handlers = new ArrayList<>();
        this.handlers.addAll(SpringUtil.getBeans(ElegantHandler.class));
        this.handlers.add((ElegantHandler) RpcSpiManager.createOrGetExtensionByClass(RpcFilter.class, "elegantRpcFilter", MyRpcRegistryManagerFactory.createOrGetMyRpcRegistryManager()));
        es.execute(new ElegantHandlerStartMonitor());
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        LogUtil.info("优雅下线接收到通知.开始通知组件停止对外提供服务!");
        //通知所有组件关闭
        notifyAllHandlerToShutdown();
        LogUtil.info("组件停止对外提供服务,监听组件行为中..");
        try {
            while (!Thread.interrupted() && anyBusy()) {
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            LogUtil.error(this, e);
        }
        LogUtil.info("组件业务完成,优雅下线结束,期待下次的相遇!");
    }


    /**
     * 通知所有组件关闭
     */
    private void notifyAllHandlerToShutdown() {
        for (ElegantHandler handler : handlers) {
            try {
                handler.notAllowToPublish();
                handler.close();
                LogUtil.info("组件:{}优雅下线成功!", handler.name());
            } catch (Exception e) {
                LogUtil.error(e, "优雅下线报错:{}", handler.name());
            }
        }
    }

    /**
     * 是否存在忙碌的应用
     *
     * @return
     */
    private boolean anyBusy() {
        for (ElegantHandler handler : handlers) {
            if (handler.isBusy()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 优雅上线组件启动监听器
     */
    private class ElegantHandlerStartMonitor implements Runnable {

        /**
         * 最大循环次数
         */
        private final Integer maxCount = 3;

        @Override
        public void run() {
            LogUtil.info("优雅上下监听启动!");
            try {
                int i = maxCount;
                while (!Thread.interrupted() && anyNotOnline() && i > 0) {
                    i--;
                    Thread.sleep(1000L);
                }
                LogUtil.info("优雅上线完成,应用开始发布服务!");
                for (ElegantHandler handler : handlers) {
                    handler.allowToPublish();
                    LogUtil.info("组件:{}优雅上线成功!", handler.name());
                }
                LogUtil.info("优雅上线结束!");
            } catch (InterruptedException e) {
                LogUtil.error(this, e);
            }

        }

        /**
         * 是否存在没有上线的应用
         *
         * @return
         */
        private boolean anyNotOnline() {
            for (ElegantHandler handler : handlers) {
                if (!handler.isOnline()) {
                    return false;
                }
            }
            return true;
        }
    }
}
