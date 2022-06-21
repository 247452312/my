package indi.uhyils.util;

import indi.uhyils.pojo.entity.type.PowerInfo;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * api权限初始化工具
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 10时40分
 */
public class ApiPowerInitUtil {


    private ApiPowerInitUtil() {
    }

    /**
     * 此工具只在未打包时有用 获取所有接口的方法 TODO 此处需要修改
     *
     * @return
     *
     * @throws IOException
     */
    public static List<PowerInfo> getPowersSingle() throws IOException {
        String basePath = new File("").getCanonicalPath();
        basePath += "\\my-api\\";
        File file = new File(basePath);
        File[] files = file.listFiles();

        List<PowerInfo> list = new ArrayList<>();
        assert files != null;
        // 遍历每个微服务的api包
        Arrays.stream(files).forEach(t -> {
            if (t.exists() && t.isDirectory()) {
                try {
                    String servicePackagePath = t.getCanonicalPath() + "/src/main/java/indi/uhyils/protocol/rpc";
                    File servicePackage = new File(servicePackagePath);
                    File[] serviceFiles = servicePackage.listFiles();
                    // 遍历微服务的每个接口
                    Arrays.stream(serviceFiles).forEach(p -> {
                        try {
                            String canonicalPath = p.getCanonicalPath();
                            //接口名称
                            String interfaceName = canonicalPath.substring(canonicalPath.lastIndexOf('\\') + 1, canonicalPath.indexOf('.'));
                            String javaClass = "indi.uhyils.protocol.rpc." + interfaceName;
                            Class<?> clazz = Class.forName(javaClass);
                            Method[] declaredMethods = clazz.getDeclaredMethods();
                            //全部方法名称
                            List<String> methodsName = Arrays.stream(declaredMethods).map(Method::getName).collect(Collectors.toList()); // 此类里面的所有方法

                            Class<?> aClass = Class.forName("indi.uhyils.protocol.rpc.base.DTOProvider");
                            //父类全部方法名称
                            List<String> fatherMethodsName = Arrays.stream(aClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
                            methodsName.addAll(fatherMethodsName);
                            list.addAll(methodsName.stream().map(v -> new PowerInfo(interfaceName, v)).collect(Collectors.toList()));

                        } catch (IOException | ClassNotFoundException e) {
                            LogUtil.error(ApiPowerInitUtil.class, e);
                        }

                    });
                } catch (IOException e) {
                    LogUtil.error(ApiPowerInitUtil.class, e);
                }
            }
        });
        return list;
    }
}
