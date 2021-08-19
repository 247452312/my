package indi.uhyils.load;

import indi.uhyils.util.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月19日 10时33分
 */
public class MyUrlClassLoader extends ShareClassLoader {

    private static final String START_CLASS = "Start-Class";

    private static final String MAIN_CLASS = "Main-Class";

    public MyUrlClassLoader(URL[] urls) {
        super(urls);
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
            } else if (key.trim().equals(MAIN_CLASS) && StringUtil.isBlack(mainClass)) {
                mainClass = value;
            }
        }
        mainClass = "org.springframework.boot.loader.JarLauncher";
        return findClass(mainClass);
    }

}
