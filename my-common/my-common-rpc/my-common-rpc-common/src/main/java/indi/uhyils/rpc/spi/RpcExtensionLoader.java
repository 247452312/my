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

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 11时20分
 */
public class RpcExtensionLoader {
    /**
     * 加载扩展点时的路径
     */
    public static final String RPC_EXTENSION_PATH = "META-INF/rpc/";

    /**
     * 加载扩展点时的扩展点保存地
     */
    public static final String RPC_EXTENSION_CLASS_PATH = "META-INF/rpcClass";
    /**
     * 已加载的所有扩展点类型
     * map<扩展点最终类,map<扩展点具体名称,扩展点实际类>>
     */
    private static Map<Class<?>, Map<String, Class<?>>> cacheClass = new HashMap<>();
    /**
     * 已加载的所有扩展点类型,缓存,注,此处list是有order的,所以在使用时用LinkedList
     * Map<root.class : target.class , 扩展点实例>
     */
    private static Map<String, List<?>> cacheExtensionPath = new HashMap<>();

    static {
        init();
    }

    /**
     * 此次加载的扩展点类型
     */
    private Class<?> type;

    public RpcExtensionLoader(Class<?> type) {
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
        // 遍历RpcExtensionLoaderTypeEnum中所有的根Class(即遍历所有扩展点类型)
        for (Class value : allRpcExtensionClass) {
            RpcExtensionLoader rpcExtensionLoader = new RpcExtensionLoader(value);
            Map<String, Class<?>> load = rpcExtensionLoader.load();
            cacheClass.put(value, load);
        }
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
                                if (RpcExtension.class.isAssignableFrom(targetClass)) {
                                    result.add(targetClass);
                                } else {
                                    LogUtil.warn(targetClass.getName() + " 没有继承: " + RpcExtension.class.getName());
                                }
                            }
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        LogUtil.error(e);
                    }
                }
            }
        } catch (IOException e) {
            LogUtil.error(RpcExtensionLoader.class, e);
            return new ArrayList<>();
        }
        return result;
    }

    public static List getExtensionByClass(Class root, Class targetClass) {

        if (!cacheClass.containsKey(root)) {
            return new ArrayList();
        }
        String cacheKey = root.getName() + " : " + targetClass.getName();
        if (cacheExtensionPath.containsKey(cacheKey)) {
            return cacheExtensionPath.get(cacheKey);
        }
        Map<String, Class<?>> classMap = cacheClass.get(root);
        LinkedList<Object> linkedList = new LinkedList<>();
        try {
            ArrayList<Class<?>> list = new ArrayList(classMap.values());
            Collections.sort(list, (o1, o2) -> {
                int o1Order = o1.getAnnotation(RpcSpi.class).order();
                int o2Order = o2.getAnnotation(RpcSpi.class).order();
                return o1Order - o2Order;
            });
            for (Class<?> value : list) {
                Constructor<?> constructor = value.getConstructor();
                if (constructor == null) {
                    throw new IllegalStateException(root.getName() + " 必须要空构造器");
                }
                if (targetClass.isAssignableFrom(value)) {
                    linkedList.add(value.newInstance());
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            LogUtil.error(e);
        }
        cacheExtensionPath.put(cacheKey, linkedList);

        return linkedList;
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
