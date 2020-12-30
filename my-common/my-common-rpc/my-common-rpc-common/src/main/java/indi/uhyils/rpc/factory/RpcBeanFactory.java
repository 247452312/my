package indi.uhyils.rpc.factory;

import indi.uhyils.rpc.annotation.MyRpc;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.rpc.util.PackageUtils;

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

        // 将bean初始化为Object先
        initBean(classByPackageName);
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
     * 首先将bean 全部初始化
     *
     * @param classByPackageName
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private final void initBean(Set<Class<?>> classByPackageName) throws Exception {
        // 首先将bean 全部初始化
        for (Class<?> clazz : classByPackageName) {
            rpcBeans.put(clazz, null);
        }
    }

    /**
     * 扫描所有的带有指定注解的包
     *
     * @param mainClass
     * @param annotation
     * @return
     */
    private final Set<Class<?>> scanAllBeanClass(Class<?> mainClass, MyRpc annotation) throws Exception {
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
