package indi.uhyils.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.PowerSimpleEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.ObjsRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

    private static final Logger logger = LoggerFactory.getLogger(PowerAddUtils.class);

    public static void initPower() throws IOException {
        String basePath = new File("").getCanonicalPath();
        basePath += "\\my-api\\";
        File file = new File(basePath);
        File[] files = file.listFiles();

        List<PowerSimpleEntity> list = new ArrayList<>();
        assert files != null;
        // 遍历每个微服务的api包
        Arrays.stream(files).forEach(t -> {
            if (t.exists() && t.isDirectory()) {
                try {
                    String servicePackagePath = t.getCanonicalPath() + "/src/main/java/indi/uhyils/service";
                    File servicePackage = new File(servicePackagePath);
                    File[] serviceFiles = servicePackage.listFiles();
                    // 遍历微服务的每个接口
                    Arrays.stream(serviceFiles).forEach(p -> {
                        try {
                            String canonicalPath = p.getCanonicalPath();
                            //接口名称
                            String interfaceName = canonicalPath.substring(canonicalPath.lastIndexOf('\\') + 1, canonicalPath.indexOf('.'));
                            String javaClass = "indi.uhyils.service." + interfaceName;
                            Class<?> clazz = Class.forName(javaClass);
                            Method[] declaredMethods = clazz.getDeclaredMethods();
                            //全部方法名称
                            List<String> methodsName = Arrays.stream(declaredMethods).map(Method::getName).collect(Collectors.toList()); // 此类里面的所有方法

                            Class<?> aClass = Class.forName("indi.uhyils.service.DefaultEntityService");
                            //父类全部方法名称
                            List<String> fatherMethodsName = Arrays.stream(aClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
                            methodsName.addAll(fatherMethodsName);
                            list.addAll(methodsName.stream().map(v -> PowerSimpleEntity.build(interfaceName, v)).collect(Collectors.toList()));

                        } catch (IOException | ClassNotFoundException e) {
                            LogUtil.error(PowerAddUtils.class, e.getMessage());
                        }

                    });
                } catch (IOException e) {
                    LogUtil.error(PowerAddUtils.class, e.getMessage());
                }
            }
        });

        ObjsRequest<PowerSimpleEntity> build = ObjsRequest.build(list);
        build.setToken("");
        LinkNode<String> requestLink = new LinkNode<>();
        requestLink.setData("初始化请求");
        build.setRequestLink(requestLink);
        ArrayList<Object> args = new ArrayList<>();
        args.add(build);
        ServiceResult<JSONArray> jsonObjectServiceResult = DubboApiUtil.dubboApiTool("PowerService", "initPowerInProStartNoToken", args, new DefaultRequest());
        if (ServiceCode.SUCCESS.getText().equals(jsonObjectServiceResult.getServiceCode())) {
            LogUtil.linkPrint(jsonObjectServiceResult.getRequestLink(), logger);
        }
        JSONArray arrayList = jsonObjectServiceResult.getData();
        arrayList.forEach(t -> {
            JSONObject obj = (JSONObject) t;
            PowerSimpleEntity powerSimpleEntity = obj.toJavaObject(PowerSimpleEntity.class);
            logger.info("添加权限: " + powerSimpleEntity.getInterfaceName() + " : " + powerSimpleEntity.getMethodName());
        });

    }


}
