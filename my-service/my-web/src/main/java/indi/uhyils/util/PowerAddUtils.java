package indi.uhyils.util;

import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.DefaultRequest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 添加权限测试用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月07日 15时02分
 */
public class PowerAddUtils {


    public static void getClassByPackage() throws IOException {
        String basePath = new File("").getCanonicalPath();
        basePath += "\\my-api\\";
        File file = new File(basePath);
        File[] files = file.listFiles();
        assert files != null;
        Arrays.stream(files).forEach(t -> {
            if (t.exists() && t.isDirectory()) {
                try {
                    String servicePackagePath = t.getCanonicalPath() + "/src/main/java/indi/uhyils/service";
                    File servicePackage = new File(servicePackagePath);
                    File[] serviceFiles = servicePackage.listFiles();
                    Arrays.stream(serviceFiles).forEach(p -> {
                        try {
                            String canonicalPath = p.getCanonicalPath();
                            String interfaceName = canonicalPath.substring(canonicalPath.lastIndexOf('\\') + 1, canonicalPath.indexOf('.'));
                            String javaClass = "indi.uhyils.service." + interfaceName;
                            Class<?> clazz = Class.forName(javaClass);
                            Method[] declaredMethods = clazz.getDeclaredMethods();
                            List<String> collect = Arrays.stream(declaredMethods).map(Method::getName).collect(Collectors.toList()); // 此类里面的所有方法

                            Class<?> aClass = Class.forName("indi.uhyils.service.DefaultEntityService");
                            List<String> collect1 = Arrays.stream(aClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
                            collect.addAll(collect1);
                            DefaultRequest defaultRequest = new DefaultRequest();
                            UserEntity user = new UserEntity();
                            user.setId("49ba59abbe56e057");
                            defaultRequest.setUser(user);
                            collect.forEach(v -> {
                                PowerEntity powerEntity = new PowerEntity();
                                powerEntity.setInterfaceName(interfaceName);
                                powerEntity.setMethodName(v);
                                powerEntity.preInsert(defaultRequest);

                                System.out.println(String.format("insert sys_power(id,create_date, create_user, update_date, update_user, delete_flag, interface_name, method_name) values ('%s', %d, '%s', %d, '%s', 0, '%s', '%s');", powerEntity.getId(), powerEntity.getCreateDate(), powerEntity.getCreateUser(), powerEntity.getUpdateDate(), powerEntity.getUpdateUser(), powerEntity.getInterfaceName(), powerEntity.getMethodName()));
                            });

                        } catch (IOException | ClassNotFoundException e) {
                            LogUtil.error(PowerAddUtils.class, e.getMessage());
                        }

                    });
                } catch (IOException e) {
                    LogUtil.error(PowerAddUtils.class, e.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        getClassByPackage();
    }


}
