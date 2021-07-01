package indi.uhyils.util;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月23日 14时11分
 */
public class ClassUtil {

    /**
     * 剥去springAop等代理类的外衣,获取真实的类的类型
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static Class<?> getRealClass(Object value) throws Exception {
        // 防止这里key是spring的aop类 或者 proxy代理
        if (!AopUtils.isAopProxy(value) && !Proxy.isProxyClass(value.getClass())) {
            return value.getClass();
        }
        Class<?> clazz = value.getClass();
        if (AopUtils.isAopProxy(value)) {
            if (AopUtils.isJdkDynamicProxy(value)) {
                clazz = getJdkDynamicProxyTargetObject(value).getClass();
            } else {
                clazz = getCglibProxyTargetObject(value).getClass();
            }
        }
        if (Proxy.isProxyClass(clazz)) {
            Class<?>[] interfaces = clazz.getInterfaces();
            clazz = interfaces[interfaces.length - 1];
        }
        return clazz;
    }


    /**
     * JDK动态代理方式被代理类的获取
     *
     * @author Monkey
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(Boolean.TRUE);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(Boolean.TRUE);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }

    /**
     * CGLIB方式被代理类的获取
     *
     * @author Monkey
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(Boolean.TRUE);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(Boolean.TRUE);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }
}
