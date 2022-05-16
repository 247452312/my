package indi.uhyils.load;

import indi.uhyils.util.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月19日 10时33分
 */
public class MyUrlClassLoader extends ShareClassLoader {

    private static final String START_CLASS = "Start-Class";

    private static final String MAIN_CLASS = "Main-Class";

    /**
     * java字节码
     */
    private final Map<String, byte[]> classAsmCache;

    public MyUrlClassLoader(URL[] urls, Map<String, byte[]> classAsmCache) {
        super(urls);
        this.classAsmCache = classAsmCache;

    }

    public Class<?> getMyMainClass() throws ClassNotFoundException, IOException {
        URL resource = findResource("META-INF/MANIFEST.MF");
        InputStream inputStream = resource.openConnection().getInputStream();
        byte[] resourceContextByteArray = new byte[inputStream.available()];
        inputStream.read(resourceContextByteArray);
        String resourceContext = new String(resourceContextByteArray);
        String[] lines = resourceContext.trim().split("\r\n");
        String mainClass = null;
        for (String line : lines) {
            String[] entity = line.split(": ");
            String key = entity[0];
            String value = entity[1];
            if (key.trim().equals(START_CLASS)) {
                mainClass = value;
                break;
            } else if (key.trim().equals(MAIN_CLASS) && StringUtil.isEmpty(mainClass)) {
                mainClass = value;
            }
        }
        mainClass = "org.springframework.boot.loader.JarLauncher";
        return findClass(mainClass);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (classAsmCache.containsKey(name)) {
            byte[] bytes = classAsmCache.get(name);
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (classAsmCache.containsKey(name)) {
            try {
                byte[] bytes = classAsmCache.get(name);
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                Method defineClass = null;
                defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
                defineClass.setAccessible(true);
                return (Class<?>) defineClass.invoke(contextClassLoader, name, bytes, 0, bytes.length);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);

    }


}
