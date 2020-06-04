package indi.uhyils.util;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.response.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * dubbo 泛化接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月24日 17时29分
 */
public class DubboApiUtil {

    /**
     * 自定义日志
     */
    private static Logger logger = LoggerFactory.getLogger(DubboApiUtil.class);

    /**
     * 接口名称包分隔符
     */
    private static final String INTERFACE_NAME_PACKAGE_SEPARATOR = ".";

    /**
     * ReferenceConfig缓存(重量级, 不缓存太慢了, 但是还没有考虑微服务过多的情况)
     */
    private static final HashMap<String, ReferenceConfig<GenericService>> MAP = new HashMap<>();

    /**
     * dubbo泛化接口调用类
     *
     * @param interfaceName 接口的名字,可以用全名或者接口名
     * @param methodName    方法名
     * @param args          方法参数
     * @param request       请求
     * @return 方法返回值
     */
    public static ServiceResult<JSONObject> dubboApiTool(String interfaceName, String methodName, List<Object> args, DefaultRequest request) {
        try {
            if (!interfaceName.contains(INTERFACE_NAME_PACKAGE_SEPARATOR)) {
                interfaceName = String.format("indi.uhyils.service.%s", interfaceName);
            }

            ReferenceConfig<GenericService> reference;
            if (MAP.containsKey(interfaceName)) {
                reference = MAP.get(interfaceName);
                if (reference == null) {
                    MAP.remove(interfaceName);
                    reference = getGenericServiceReferenceConfig(interfaceName);
                    MAP.put(interfaceName, reference);
                }
            } else {
                reference = getGenericServiceReferenceConfig(interfaceName);
                MAP.put(interfaceName, reference);
            }

            // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
            GenericService genericService = reference.get();

            /*
             * GenericService 这个接口只有一个方法，名为 $invoke，它接受三个参数，分别为方法名、方法参数类型数组和参数值数组；
             * 对于方法参数类型数组 如果是基本类型，如 int 或 long，可以使用 int.class.getName()获取其类型； 如果是基本类型数组，如
             * int[]，则可以使用 int[].class.getName()； 如果是 POJO，则直接使用全类名，如
             * com.alibaba.dubbo.samples.generic.api.Params。
             */
            //全部方法
            Method[] methods = Class.forName(interfaceName).getMethods();

            Method method = Arrays.stream(methods).filter(m -> methodName.equalsIgnoreCase(m.getName()) && args.size() == m.getParameterTypes().length).findFirst().get();

            Class[] params = method.getParameterTypes();

            Object[] arg = args.toArray(new Object[0]);
            String[] parameterTypes = Arrays.stream(params).map(Class::getName).toArray(String[]::new);
            ServiceResult<JSONObject> serviceResult = JSONObject.parseObject(JSONObject.toJSONString(genericService.$invoke(methodName, parameterTypes, arg)), ServiceResult.class);
            request.setRequestLink(serviceResult.getRequestLink());
            return serviceResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return ServiceResult.buildErrorResult("远程调用错误,具体见日志", request);
        }
    }

    private static ReferenceConfig<GenericService> getGenericServiceReferenceConfig(String interfaceName) {
        ReferenceConfig<GenericService> reference;
        reference = new ReferenceConfig<>();
        // 弱类型接口名
        reference.setInterface(interfaceName);
        // 设置分组名称
        reference.setGroup(SpringUtil.getApplicationContext().getEnvironment().getActiveProfiles()[0]);

        // 声明为泛化接口
        reference.setGeneric(true);
        reference.setApplication(SpringUtil.getBean(ApplicationConfig.class));
        reference.setRegistry(SpringUtil.getBean(RegistryConfig.class));
        reference.setConsumer(SpringUtil.getBean(ConsumerConfig.class));
        return reference;
    }

}
