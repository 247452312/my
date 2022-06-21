package indi.uhyils.load;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * 共享classLoader
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月16日 08时56分
 */
public class ShareClassLoader extends URLClassLoader {

    public ShareClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public ShareClassLoader(URL[] urls) {
        super(urls);
    }

    public ShareClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
