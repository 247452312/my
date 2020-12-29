package indi.uhyils.rpc.factory;

import indi.uhyils.rpc.annotation.MyRpc;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.rpc.exception.ClassNotRpcBeanException;
import indi.uhyils.rpc.util.PackageUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * rpc下的bean工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月29日 06时54分
 */
public class RpcBeanFactory {


    /**
     * 单例
     */
    private volatile static RpcBeanFactory instance;
    /**
     * 是否初始化
     */
    private final Boolean init;
    /**
     * rpc所在的bean
     */
    private Map<Class<?>, Object> rpcBeans = new HashMap<>();

    private RpcBeanFactory(Class<?> mainClass) throws Exception {
        // 如果没有启动类的注解,则表示此次启动不使用Rpc,将初始化标志赋值为false,表示不初始化,此类禁止使用
        MyRpc annotation = mainClass.getAnnotation(MyRpc.class);
        if (annotation == null) {
            init = false;
            return;
        }
        init = true;
        Set<Class<?>> classByPackageName = scanAllBeanClass(mainClass, annotation);

        // 将bean初始化
        initBean(classByPackageName);

        for (Map.Entry<Class<?>, Object> entry : rpcBeans.entrySet()) {
            Object bean = entry.getValue();
            Class<?> clazz = bean.getClass();
            // 先注入field
            initBeanField(bean, clazz);

            // 执行一遍方法
            invokeBeanMethod(bean, clazz);
        }
    }

    /**
     * 双重检测锁
     *
     * @return
     */
    public static RpcBeanFactory getInstance(Class<?> mainClass) throws Exception {
        if (null == instance) {
            if (mainClass == null) {
                throw new RuntimeException("rpc bean工厂第一次初始化需要参数");
            }
            synchronized (RpcBeanFactory.class) {
                if (null == instance) {
                    instance = new RpcBeanFactory(mainClass);
                }
            }
        }
        return instance;
    }

    /**
     * 双重检测锁
     *
     * @return
     */
    public static RpcBeanFactory getInstance() throws Exception {
        return getInstance(null);
    }

    /**
     * 执行一遍带有注解的方法
     *
     * @param bean
     * @param clazz
     * @throws ClassNotRpcBeanException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public final void invokeBeanMethod(Object bean, Class<?> clazz) throws Exception {
        Method[] declaredMethods = clazz.getMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            RpcReference methodAnnotation = declaredMethod.getAnnotation(RpcReference.class);
            if (methodAnnotation == null) {
                continue;
            }
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            boolean allAreBean = true;
            Class<?> mark = null;
            // 首先扫描类的参数是否都是bean
            for (Class<?> parameterType : parameterTypes) {
                if (rpcBeans.containsKey(parameterType.getName())) {
                    continue;
                }
                allAreBean = false;
                mark = parameterType;
                break;
            }
            // 存在不是bean的
            if (!allAreBean) {
                throw new ClassNotRpcBeanException(mark);
            }
            //这里代表着方法所有的参数都是bean
            Object[] params = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                String paramTypeName = parameterTypes[i].getName();
                Object paramBean = rpcBeans.get(paramTypeName);
                params[i] = paramBean;
            }
            // 执行一遍方法,这里的返回值... 怎么搞 fixme
            declaredMethod.invoke(bean, params);
        }
    }

    /**
     * 执行一遍带有field的方法
     *
     * @param bean
     * @param clazz
     * @throws IllegalAccessException
     * @throws ClassNotRpcBeanException
     */
    public final void initBeanField(Object bean, Class<?> clazz) throws Exception {
        // 获取所有属性,有注解的注入
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            RpcReference fieldRpcReference = field.getAnnotation(RpcReference.class);
            // 属性上没有Reference注解,则跳过此属性
            if (fieldRpcReference == null) {
                continue;
            }
            Class<?> type = field.getType();
            if (rpcBeans.containsKey(type.getName())) {
                field.set(bean, rpcBeans.get(type.getName()));
            } else {
                throw new ClassNotRpcBeanException(field.getType());
            }
        }
    }

    /**
     * 首先将bean 全部初始化
     *
     * @param classByPackageName
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public final void initBean(Set<Class<?>> classByPackageName) throws Exception {
        // 首先将bean 全部初始化
        for (Class<?> clazz : classByPackageName) {
            Object o = clazz.newInstance();
            rpcBeans.put(clazz, o);
        }
    }

    /**
     * 扫描所有的带有指定注解的包
     *
     * @param mainClass
     * @param annotation
     * @return
     */
    public final Set<Class<?>> scanAllBeanClass(Class<?> mainClass, MyRpc annotation) throws Exception {
        // 获取所有的扫描包,注意, 这里要加上mainClass本身所在的包
        String[] scanPackage = annotation.baseScanPackage();
        String[] excludePackage = annotation.excludePackage();
        Set<Class<?>> classByPackageName = new HashSet<>();
        for (String packageName : scanPackage) {
            classByPackageName.addAll(PackageUtils.getClassByPackageName(packageName, excludePackage, true));
        }
        // mainClass本身所在的包
        classByPackageName.addAll(PackageUtils.getClassByPackageName(mainClass.getPackage().getName(), excludePackage, true));
        Set<Class<?>> result = new HashSet<>();
        for (Class<?> clazz : classByPackageName) {
            RpcService rpcService = clazz.getAnnotation(RpcService.class);
            if (rpcService != null) {
                result.add(clazz);
            }
        }
        return result;
    }

    public Map<Class<?>, Object> getRpcBeans() {
        return rpcBeans;
    }
}
