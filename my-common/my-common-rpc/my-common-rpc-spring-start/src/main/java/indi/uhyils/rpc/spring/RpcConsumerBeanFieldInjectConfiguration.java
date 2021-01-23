package indi.uhyils.rpc.spring;

import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.rpc.proxy.RpcProxyFactory;
import indi.uhyils.rpc.registry.exception.RegistryException;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月17日 09时55分
 */
public class RpcConsumerBeanFieldInjectConfiguration implements ApplicationContextAware, InstantiationAwareBeanPostProcessor {
    /**
     * 注册类的缓存
     */
    private volatile static Map<String, Object> consumerRegistryCache = new ConcurrentHashMap<>();
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 注入bean中的带有{@link RpcReference}属性或者方法
     *
     * @param bean
     * @param beanName
     * @return
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        fieldInject(bean);
        methodInject(bean);
        return true;
    }

    /**
     * 通过方法注入, 方法入参要同时为远程方法
     *
     * @param bean 类实例
     */
    private void methodInject(Object bean) {
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
            if (isHaveInitProxy(declaredAnnotations)) {
                try {
                    Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                    for (Class<?> type : parameterTypes) {
                        initConsumerByType(type);
                    }
                    Object[] params = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {
                        params[i] = consumerRegistryCache.get(parameterTypes[i].getName());
                    }
                    declaredMethod.invoke(bean, params);
                } catch (RegistryException | IllegalAccessException | InvocationTargetException e) {
                    LogUtil.error(this, e);
                }
            }
        }
    }

    /**
     * 通过属性进行注入
     *
     * @param bean 类实例
     */
    private void fieldInject(Object bean) {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            if (isHaveInitProxy(declaredAnnotations)) {
                try {
                    Class<?> type = declaredField.getType();
                    initConsumerByType(type);
                    declaredField.set(bean, consumerRegistryCache.get(type.getName()));

                } catch (RegistryException | IllegalAccessException e) {
                    LogUtil.error(this, e);
                }
            }
        }
    }

    private boolean isHaveInitProxy(Annotation[] declaredAnnotations) {
        boolean haveInitProxy = false;
        for (Annotation declaredAnnotation : declaredAnnotations) {
            if (declaredAnnotation instanceof RpcReference) {
                haveInitProxy = true;
                break;
            }
        }
        return haveInitProxy;
    }

    private void initConsumerByType(Class<?> type) throws RegistryException {
        if (consumerRegistryCache.get(type.getName()) == null) {
            synchronized (consumerRegistryCache) {
                if (consumerRegistryCache.get(type.getName()) == null) {
                    Object o = RpcProxyFactory.newProxy(type);
                    consumerRegistryCache.put(type.getName(), o);
                }
            }
        }
    }

}
