package indi.uhyils.rpc.proxy.handler;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.ConsumerConfig;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.netty.spi.step.RpcStep;
import indi.uhyils.rpc.netty.spi.step.template.ConsumerResponseObjectExtension;
import indi.uhyils.rpc.registry.Registry;
import indi.uhyils.rpc.registry.RegistryFactory;
import indi.uhyils.rpc.spi.RpcSpiManager;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.IpUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 默认的rpc代理类,自己实现代理见{@link RpcProxyHandlerInterface}
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月28日 06时47分
 * @see RpcProxyHandlerInterface
 */
@RpcSpi(single = false)
public class RpcProxyDefaultHandler implements RpcProxyHandlerInterface {
    private static final String TO_STRING = "toString";
    /**
     * 这个handler代理的类
     */
    private Class<?> type;

    /**
     * 注册类
     */
    private Registry registry;

    /**
     * 消费者接收回复Object拦截器
     */
    private List<ConsumerResponseObjectExtension> consumerResponseObjectExtensions;


    /**
     * 启动时初始化
     *
     * @param clazz
     */
    public RpcProxyDefaultHandler(Class<?> clazz) {
        init(clazz);
    }

    /**
     * 启动时不初始化 兼容spi
     */
    public RpcProxyDefaultHandler() {
    }

    @Override
    public void init(Class<?> clazz) {
        this.type = clazz;
        // 如果懒加载,那么就不加载
        if (isCheck()) {
            try {
                this.registry = RegistryFactory.createConsumer(clazz, IpUtil.getIp());
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
        }
        consumerResponseObjectExtensions = RpcSpiManager.getExtensionByClass(RpcStep.class, ConsumerResponseObjectExtension.class);
    }

    private boolean isCheck() {
        RpcConfig instance = RpcConfigFactory.getInstance();
        ConsumerConfig consumer = instance.getConsumer();
        return consumer.getCheck();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (registry == null) {
            try {
                this.registry = RegistryFactory.createConsumer(type, IpUtil.getIp());
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
        }

        // 防止proxy自动调用toString方法导致的报错
        if (TO_STRING.equals(method.getName())) {
            return "this is the interface,it`s name is " + proxy.getClass().getSimpleName();
        }
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        String invoke = registry.invoke(bean.newId(), method.getName(), method.getParameterTypes(), args);
        Object o = JSON.parseObject(invoke, method.getGenericReturnType());
        for (ConsumerResponseObjectExtension consumerResponseObjectExtension : consumerResponseObjectExtensions) {
            o = consumerResponseObjectExtension.doFilter(o, invoke);
        }
        return o;
    }
}
