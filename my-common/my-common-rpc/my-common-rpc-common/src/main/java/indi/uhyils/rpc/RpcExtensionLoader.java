package indi.uhyils.rpc;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.util.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 11时20分
 */
public class RpcExtensionLoader<T> {
    /**
     * 加载扩展点时的路径
     */
    public static final String RPC_EXTENSION_PATH = "META-INF/rpc/";
    /**
     * 已加载的所有扩展点类型,缓存,注,此处list是有order的,所以在使用时用LinkedList
     */
    private static Map<Class<?>, List<?>> cache = new HashMap<>();
    /**
     * 此次加载的扩展点类型
     */
    private Class<T> type;

    /**
     * 判断clazz是否是
     *
     * @param clazz
     * @return
     */
    private static boolean haveSpi(Class<?> clazz) {
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof RpcSpi) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Class<T>> load() {
        Map<String, Class<T>> extensions = new HashMap<>(16);
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
    private void loadDirs(Map<String, Class<T>> extensions, String dir, String name) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(dir + name);
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
    private void loadResources(Map<String, Class<T>> extensions, ClassLoader classLoader, URL url) {
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
    private void loadClass(Map<String, Class<T>> extensions, Class<?> clazz, String name) {
        if (!type.isAssignableFrom(clazz)) {
            throw new IllegalStateException(clazz.getName() + " 不是 " + type.getName());
        }
        if (haveSpi(clazz)) {
            // todo 暂停.提交一下子,然后继续写
        }
    }


}
