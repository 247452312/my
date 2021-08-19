package indi.uhyils.test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月18日 22时41分
 */
public class Test {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new File("D:/share/ideaSrc/my/my-service/my-service-all/src/main/resources/my-web-0.3.0-my-SNAPSHOT.jar").toURI().toURL()});
        Class<?> aClass = classLoader.loadClass("org.springframework.boot.loader.JarLauncher");
        Method main = aClass.getDeclaredMethod("main", String[].class);
        main.setAccessible(true);
        Object invoke = main.invoke(null,  new Object[]{args});

    }


}
