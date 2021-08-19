package indi.uhyils.load;

import java.net.URL;
import java.net.URLClassLoader;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月19日 10时33分
 */
public class MyUrlClassLoader extends URLClassLoader {

    public MyUrlClassLoader(URL[] urls) {
        super(urls);
    }

    public Class<?> getMyMainClass() throws ClassNotFoundException {
        return findClass("org.springframework.boot.loader.JarLauncher");
    }
}
