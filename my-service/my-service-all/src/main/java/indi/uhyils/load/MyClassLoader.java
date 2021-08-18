package indi.uhyils.load;

import indi.uhyils.load.model.LoadModel;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.MapUtil;
import indi.uhyils.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

    private static final String JAR_FILE = ".jar";

    private final Map<String, byte[]> classCache;

    private final Map<String, byte[]> sourceCache;


    public MyClassLoader(Map<String, byte[]> classCache, Map<String, byte[]> sourceCache) {
        this.classCache = classCache;
        this.sourceCache = sourceCache;
    }

    public static MyClassLoader newInstall(String jarPath) throws IOException {
        LoadModel loadModel = loadJar(jarPath);
        return new MyClassLoader(loadModel.getClassCache(), loadModel.getSourceCache());
    }

    /**
     * 加载jar包中的文件
     *
     * @param jarPath
     *
     * @return
     *
     * @throws IOException
     */
    public static LoadModel loadJar(String jarPath) throws IOException {
        try (JarFile jarFile = new JarFile(new File(jarPath))) {
            Enumeration<JarEntry> entries = jarFile.entries();
            Map<String, byte[]> classMap = new HashMap<>();
            Map<String, byte[]> sourceMap = new HashMap<>();
            LoadModel build = LoadModel.build(classMap, sourceMap);
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(CLASS_FILE)) {
                    if (classMap.containsKey(name)) {
                        continue;
                    }
                    fileCacheMap(jarFile, jarEntry, classMap, name, CLASS_FILE);
                } else if (!name.endsWith(SLASH) && !name.endsWith(JAR_FILE)) {
                    // 资源文件
                    fileCacheMap(jarFile, jarEntry, sourceMap, name, JAR_FILE);
                }
            }
            entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(JAR_FILE)) {
                    //jar 递归
                    LoadModel loadModel = loadJar(jarPath + "!/" + name);
                    build.putAllIfAbsent(loadModel);
                }

            }
            return build;
        }
    }

    private static void fileCacheMap(JarFile jarFile, JarEntry jarEntry, Map<String, byte[]> sourceMap, String name, String jarFile2) throws IOException {
        byte[] bytes;
        try (InputStream inputStream = jarFile.getInputStream(jarEntry)) {
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        }
        name = name.substring(0, name.lastIndexOf(jarFile2));
        if (name.startsWith(JAR_PREFIX)) {
            name = name.substring(JAR_PREFIX.length());
        }
        name = name.replace(SLASH, SPOT);
        sourceMap.put(name, bytes);
    }

    @Override
    protected URL findResource(String name) {
        try {
            return new URI("").toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            LogUtil.error(e);
        }
        return super.findResource(name);
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
