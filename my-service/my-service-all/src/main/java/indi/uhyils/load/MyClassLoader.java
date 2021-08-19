package indi.uhyils.load;

import indi.uhyils.util.MapUtil;
import indi.uhyils.util.StringUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 每一个应用的classLoader
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月16日 08时52分
 */
public class MyClassLoader extends URLClassLoader {


    private static final String JAR_PREFIX = "/BOOT-INF/classes/";

    private static final String META_INF_PREFIX = "META-INF/classes/";

    private static final String SLASH = "/";

    private static final String SPOT = ".";

    private static final String CLASS_FILE = ".class";

    private static final String JAR_FILE = ".jar";

    private static final String PACKAGE = "indi.uhyils";

    private final Map<String, byte[]> classCache;

    private final Map<String, byte[]> sourceCache;

    private final Map<String, URL> sourceUrlCache;

    public MyClassLoader(Map<String, byte[]> classCache, Map<String, byte[]> sourceCache, Map<String, URL> sourceUrlCache) {
        super(new URL[]{});
        this.classCache = classCache;
        this.sourceCache = sourceCache;
        this.sourceUrlCache = sourceUrlCache;
    }

    public static MyClassLoader newInstall(String dirPath) throws IOException {
        return loadPath(dirPath);
    }

    public static MyClassLoader loadPath(String dirPath) throws IOException {
        dirPath = dirPath.replace("\\", "/");
        File dirFile = new File(dirPath);
        Map<String, byte[]> classMap = new HashMap<>();
        Map<String, byte[]> sourceMap = new HashMap<>();
        Map<String, URL> sourceUrlCache = new HashMap<>();
        MyClassLoader myClassLoader = new MyClassLoader(classMap, sourceMap, sourceUrlCache);
        ArrayList<File> fileList = new ArrayList<>();
        loadFiles(dirFile, fileList);

        for (File file : fileList) {
            String absolutePath = file.getAbsolutePath();
            absolutePath = absolutePath.replace("\\", "/");
            String path = absolutePath.substring(dirPath.length());

            if (path.endsWith(CLASS_FILE)) {
                if (classMap.containsKey(path)) {
                    continue;
                }
                FileInputStream inputStream = new FileInputStream(absolutePath);
                fileCacheMap(inputStream, classMap, path, CLASS_FILE);
                inputStream.close();
            } else if (!path.endsWith(SLASH) && !path.endsWith(JAR_FILE)) {
                // 资源文件
                File sourceFile = new File(absolutePath);
                if (path.startsWith(JAR_PREFIX)) {
                    path = path.substring(JAR_PREFIX.length());
                }
                sourceUrlCache.putIfAbsent(path, sourceFile.toURI().toURL());
            } else if (path.endsWith(JAR_FILE)) {
                loadJar(myClassLoader, absolutePath);
            }
        }
        return myClassLoader;
    }

    /**
     * 递归加载文件
     *
     * @param file
     * @param fileList
     */
    private static void loadFiles(File file, List<File> fileList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                loadFiles(file1, fileList);
            }
        } else {
            fileList.add(file);
        }
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
    public static void loadJar(MyClassLoader classLoader, String jarPath) throws IOException {
        classLoader.addURL(new File(jarPath).toURI().toURL());
    }

    private static void fileCacheMap(InputStream inputStream, Map<String, byte[]> sourceMap, String name, String mark) throws IOException {
        byte[] bytes;
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);

        if (StringUtil.isNotBlack(mark)) {
            name = name.substring(0, name.lastIndexOf(mark));
        }
        if (name.startsWith(JAR_PREFIX)) {
            name = name.substring(JAR_PREFIX.length());
        }
        if (name.startsWith(META_INF_PREFIX)) {
            name = name.substring(META_INF_PREFIX.length());
        }
        if (CLASS_FILE.equals(mark)) {
            if (name.startsWith(SLASH)) {
                name = name.substring(1);
            }
            name = name.replace(SLASH, SPOT);
        }
        sourceMap.put(name, bytes);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    @Override
    public URL findResource(String name) {
        if (sourceUrlCache.containsKey(name)) {
            return sourceUrlCache.get(name);
        }
        if (sourceCache.containsKey(name)) {
            byte[] bytes = sourceCache.get(name);
            try {
                URL url = new URI(new String(bytes)).toURL();
                sourceUrlCache.put(name, url);
                return url;
            } catch (MalformedURLException | URISyntaxException e) {
                e.printStackTrace();
            }
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
            return findClass("org.springframework.boot.loader.JarLauncher");
            /*for (Entry<String, byte[]> entry : classCache.entrySet()) {
                String key = entry.getKey();
                if (key.startsWith(PACKAGE) && StringUtil.containsCount(key, '.') == 2) {
                    return findClass(key);
                }
            }*/
        }
        return null;
    }
}

