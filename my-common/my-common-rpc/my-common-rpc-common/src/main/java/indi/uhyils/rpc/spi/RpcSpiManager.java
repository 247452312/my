package indi.uhyils.rpc.spi;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.util.LogUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 11时20分
 */
public class RpcSpiManager {
    /**
     * 加载扩展点时的路径
     */
    private static final String RPC_EXTENSION_PATH = "META-INF/rpc/";

    /**
     * 加载扩展点时的扩展点保存地
     */
    private static final String RPC_EXTENSION_CLASS_PATH = "META-INF/rpcClass";
    /**
     * 已加载的所有扩展点类型
     * map<扩展点最终类,map<扩展点具体名称,扩展点实际类>>
     */
    private static Map<Class<?>, Map<String, Class<?>>> cacheClass = new HashMap<>();
    /**
     * 已加载的所有扩展点类型,缓存,注,此处list是有order的,所以在使用时用LinkedList
     * Map<root.class : target.class , 扩展点实例>
     */
    private static Map<String, List<RpcSpiExtension>> cacheExtensionPath = new HashMap<>();
    /**
     * cacheClass 对应的实例
     */
    private static Map<Class, Map<String, RpcSpiExtension>> cacheClassInstanceMap = new HashMap<>();

    static {
        init();
    }

    /**
     * 此次加载的扩展点类型
     */
    private Class<?> type;

    public RpcSpiManager(Class<?> type) {
        this.type = type;
    }

    /**
     * 初始化本类
     */
    private static void init() {
        // 加载所有的需要扩展的扩展点
        List<Class> allRpcExtensionClass = loadAllRpcExtensionClass();
        if (allRpcExtensionClass == null) {
            return;
        }
        // 遍历所有扩展点类型
        for (Class value : allRpcExtensionClass) {
            RpcSpiManager rpcExtensionLoader = new RpcSpiManager(value);
            Map<String, Class<?>> load = rpcExtensionLoader.load();
            cacheClass.put(value, load);
        }

        /**
         * todo 应该扫{@link indi.uhyils.rpc.annotation.MyRpc}下的所有类,扫描出需要扩展的扩展点
         */
    }

    /**
     * 加载所有的需要扩展的扩展点
     *
     * @return
     */
    private static List<Class> loadAllRpcExtensionClass() {
        List<Class> result = new LinkedList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            // 从META-INF/rpcClass 这个文件中按行取出类
            Enumeration<URL> resources = classLoader.getResources(RPC_EXTENSION_CLASS_PATH);
            if (!resources.hasMoreElements()) {
                resources = ClassLoader.getSystemResources(RPC_EXTENSION_CLASS_PATH);
            }
            if (resources != null) {
                while (resources.hasMoreElements()) {
                    URL url = resources.nextElement();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
                        String className;
                        while ((className = br.readLine()) != null) {
                            // 删除注释
                            int i = className.indexOf("#");
                            if (i >= 0) {
                                className = className.substring(0, i);
                            }
                            className = className.trim();
                            if (className.length() > 0) {
                                Class<?> targetClass = Class.forName(className, true, classLoader);
                                if (RpcSpiExtension.class.isAssignableFrom(targetClass)) {
                                    result.add(targetClass);
                                } else {
                                    LogUtil.warn(targetClass.getName() + " 没有继承: " + RpcSpiExtension.class.getName());
                                }
                            }
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        LogUtil.error(e);
                    }
                }
            }
        } catch (IOException e) {
            LogUtil.error(RpcSpiManager.class, e);
            return new ArrayList<>();
        }
        return result;
    }

    /**
     * 根据名称获取指定扩展点的类
     *
     * @param root 指定扩展点(rpcClass文件中的类)
     * @param name 名称,唯一
     * @return
     */
    public static <T extends RpcSpiExtension> RpcSpiExtension getExtensionByClass(Class<T> root, String name) {
        // 看看加载的类中是否有目标root
        if (!cacheClass.containsKey(root)) {
            return null;
        }

        // 初始化缓存,加速获取用
        if (!cacheClassInstanceMap.containsKey(root)) {
            int size = cacheClass.get(root).size();
            cacheClassInstanceMap.put(root, new HashMap<>(size));
        }

        // 如果应该加载的地方没有此名称
        if (!cacheClass.get(root).containsKey(name)) {
            return null;
        }

        // 查询之前缓存里有没有
        Map<String, RpcSpiExtension> cacheMap = cacheClassInstanceMap.get(root);
        if (cacheMap.containsKey(name)) {
            return checkSingleAndGetResult(cacheMap.get(name));
        } else {
            // 之前缓存里也没有 只能初始化一次了
            Class<?> clazz = cacheClass.get(root).get(name);
            Constructor<?> constructor;
            try {
                // 获取空的构造器
                constructor = clazz.getConstructor();
            } catch (Exception e) {
                throw new IllegalStateException(root.getName() + " 必须要空构造器");
            }
            RpcSpiExtension instance = null;
            try {
                // 初始化
                instance = (RpcSpiExtension) constructor.newInstance();
            } catch (Exception e) {
                LogUtil.error(RpcSpiManager.class, e);
            }
            //放入缓存
            cacheMap.put(name, instance);
            return checkSingleAndGetResult(instance);
        }
    }

    /**
     * 扫描spi是否是单例,如果不是,则使用clone方法
     *
     * @param obj
     * @return
     */
    private static <T extends RpcSpiExtension> T checkSingleAndGetResult(T obj) {
        if (obj == null) {
            return null;
        }

        RpcSpi annotation = obj.getClass().getAnnotation(RpcSpi.class);
        // 如果是单例模式,直接返回
        if (annotation.single()) {
            return obj;
        } else {
            //调用clone方法
            try {
                return (T) obj.rpcClone();
            } catch (IllegalAccessException | InstantiationException e) {
                LogUtil.error(RpcSpiManager.class, e);
                return null;
            }
        }
    }

    /**
     * 根据rpcClass中的类作为root,以目标class获取指定的所有类,根据order排序的linkList
     *
     * @param root        rpcClass中定义的类
     * @param targetClass 要获取的类
     * @return
     */
    public static <T extends RpcSpiExtension, E extends T> List<E> getExtensionByClass(Class<T> root, Class<E> targetClass) {
        // 看看加载的类中是否有目标root
        if (!cacheClass.containsKey(root)) {
            return new ArrayList();
        }
        // 缓存,加速获取用
        if (!cacheClassInstanceMap.containsKey(root)) {
            int size = cacheClass.get(root).size();
            cacheClassInstanceMap.put(root, new HashMap<>(size));
        }
        //缓存中的key,如果有 直接返回
        String cacheKey = root.getName() + " : " + targetClass.getName();
        if (cacheExtensionPath.containsKey(cacheKey)) {
            List<RpcSpiExtension> objects = cacheExtensionPath.get(cacheKey);
            return objects.stream().map(t -> (E) t).map(RpcSpiManager::checkSingleAndGetResult).filter(Objects::nonNull).collect(Collectors.toList());
        }
        final Map<String, Class<?>> classMap = cacheClass.get(root);
        ArrayList<String> list = new ArrayList<>(classMap.keySet());
        // 获取结果,注 这里面不是克隆的 而是原型模式中的原型 所以不需要lone
        List<RpcSpiExtension> result = list.stream()
                //将对应的转换为object 缓存
                .map(value -> {
                    Class<?> clazz = classMap.get(value);
                    // 判断是不是指定的需要的targetClass的子类
                    if (!targetClass.isAssignableFrom(clazz)) {
                        return null;
                    }
                    Constructor<?> constructor;
                    try {
                        // 获取空的构造器
                        constructor = clazz.getConstructor();
                    } catch (Exception e) {
                        throw new IllegalStateException(root.getName() + " 必须要空构造器");
                    }

                    //从缓存中拿出已经有的,防止重复初始化
                    Map<String, RpcSpiExtension> cacheClassRootMap = cacheClassInstanceMap.get(root);
                    if (cacheClassRootMap.containsKey(value)) {
                        return cacheClassRootMap.get(value);
                    } else {
                        RpcSpiExtension instance = null;
                        try {
                            // 初始化
                            instance = (RpcSpiExtension) constructor.newInstance();
                        } catch (Exception e) {
                            LogUtil.error(RpcSpiManager.class, e);
                        }
                        cacheClassRootMap.put(value, instance);
                        return instance;
                    }
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(value -> value.getClass().getAnnotation(RpcSpi.class).order())).collect(Collectors.toList());

        cacheExtensionPath.put(cacheKey, result);

        return result.stream().map(t -> (E) t).map(RpcSpiManager::checkSingleAndGetResult).filter(Objects::nonNull).collect(Collectors.toList());
    }


    /**
     * 判断clazz是否是扩展点
     *
     * @param clazz
     * @return
     */
    private static boolean haveSpi(Class<?> clazz) {
        return clazz.getAnnotation(RpcSpi.class) != null;
    }

    /**
     * 查询className
     *
     * @param clazz
     * @return
     */
    private String findClassName(Class<?> clazz) {
        RpcSpi annotation = clazz.getAnnotation(RpcSpi.class);
        if (annotation == null || StringUtils.isEmpty(annotation.name())) {
            String name = clazz.getSimpleName();
            if (name.endsWith(type.getSimpleName())) {
                name = name.substring(0, name.length() - type.getSimpleName().length());
            }
            return name.toLowerCase();
        }
        return annotation.name();
    }

    public Map<String, Class<?>> load() {
        Map<String, Class<?>> extensions = new HashMap<>(16);
        loadDirs(extensions, RPC_EXTENSION_PATH, type.getName());
        return extensions;
    }

    /**
     * 读取执行resource所在的文件夹
     *
     * @param extensions
     * @param dir
     * @param name
     */
    private void loadDirs(Map<String, Class<?>> extensions, String dir, String name) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            String fileName = dir + name;
            Enumeration<URL> resources = classLoader.getResources(fileName);
            if (!resources.hasMoreElements()) {
                resources = ClassLoader.getSystemResources(fileName);
            }
            if (resources != null) {
                while (resources.hasMoreElements()) {
                    URL url = resources.nextElement();
                    loadResources(extensions, classLoader, url);
                }
            }
        } catch (IOException e) {
            LogUtil.error(e, "找不到资源文件" + dir + name);
        }
    }

    /**
     * 加载每一个resource
     *
     * @param extensions
     * @param classLoader
     * @param url
     */
    private void loadResources(Map<String, Class<?>> extensions, ClassLoader classLoader, URL url) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                int i = line.indexOf("#");
                if (i >= 0) {
                    line = line.substring(0, i);
                }
                line = line.trim();
                if (line.length() > 0) {
                    String name = null;
                    int j = line.indexOf("=");
                    if (j >= 0) {
                        name = line.substring(0, j).trim();
                        line = line.substring(j + 1).trim();
                    }
                    if (line.length() > 0) {
                        loadClass(extensions, Class.forName(line, true, classLoader), name);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LogUtil.error(e);
        }
    }

    /**
     * 加载resource中的每一个class
     *
     * @param extensions
     * @param clazz
     * @param name
     */
    private void loadClass(Map<String, Class<?>> extensions, Class<?> clazz, String name) {
        if (!type.isAssignableFrom(clazz)) {
            throw new IllegalStateException(clazz.getName() + " 不是 " + type.getName());
        }
        if (haveSpi(clazz)) {
            // 要注入了
            if (name == null) {
                name = findClassName(clazz);
                if (StringUtils.isEmpty(name)) {
                    throw new IllegalStateException("读取扩展配置文件时找不到name:" + clazz.getName());
                }
            }
            extensions.put(name, clazz);
        }
    }


}
