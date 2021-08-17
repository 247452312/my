package indi.uhyils.load;

import indi.uhyils.util.LogUtil;
import indi.uhyils.util.MapUtil;
import indi.uhyils.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.commons.lang3.StringUtils;

/**
 * 每一个应用的classLoader
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月16日 08时52分
 */
public class MyClassLoader extends ClassLoader {


    private static final String JAR_PREFIX = "BOOT-INF/classes/";

    private static final String SLASH = "/";

    private static final String SPOT = ".";

    private static final String CLASS_FILE = ".class";

    private final Map<String, byte[]> classCache;

    public MyClassLoader(Map<String, byte[]> classCache) {
        this.classCache = classCache;
    }

    public static MyClassLoader newInstall(String jarPath, String packagePrefix) {
        if (StringUtils.isBlank(packagePrefix)) {
            packagePrefix = SPOT;
        }
        packagePrefix = packagePrefix.replace(SPOT, SLASH);
        File file = new File(jarPath);
        try (JarFile jarFile = new JarFile(file)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            Map<String, byte[]> map = new HashMap<>();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(CLASS_FILE) && name.contains(packagePrefix)) {
                    byte[] bytes;
                    try (InputStream inputStream = jarFile.getInputStream(jarEntry)) {
                        bytes = new byte[inputStream.available()];
                        inputStream.read(bytes);
                    }
                    int i1 = name.lastIndexOf(CLASS_FILE);
                    name = name.substring(0, i1);
                    if (name.startsWith(JAR_PREFIX)) {
                        name = name.substring(JAR_PREFIX.length());
                    }
                    name = name.replace(SLASH, SPOT);
                    map.put(name, bytes);
                } else if (!name.endsWith(SLASH)) {
                    // TODO source还没有读取 先暂时写一个能跑起来 之后再重构
                }
            }
            return new MyClassLoader(map);
        } catch (IOException e) {
            LogUtil.error(e);
        }
        return null;

    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (classCache.containsKey(name)) {
            byte[] bytes = classCache.get(name);
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }

    public Class<?> getMyMainClass() throws ClassNotFoundException {
        if (MapUtil.isNotEmpty(classCache)) {
            for (Entry<String, byte[]> entry : classCache.entrySet()) {
                String key = entry.getKey();
                if (StringUtil.containsCount(key, '.') == 2) {
                    return findClass(key);
                }
            }
        }
        return null;
    }

}
