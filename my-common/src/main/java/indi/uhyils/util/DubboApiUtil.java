package indi.uhyils.util;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.response.ServiceResult;

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
     * ReferenceConfig缓存(重量级, 不缓存太慢了, 但是还没有考虑微服务过多的情况)
     */
    private static final HashMap<String, ReferenceConfig<GenericService>> map = new HashMap<>();

    /**
     * dubbo泛化接口调用类
     *
     * @param interfaceName 接口的名字,可以用全名或者接口名
     * @param methodName    方法名
     * @param args          方法参数
     * @return 方法返回值
     * @throws ClassNotFoundException
     */
    public static ServiceResult dubboApiTool(String interfaceName, String methodName, List<Object> args) throws ClassNotFoundException {

        if (interfaceName.contains(".") != true) {
            interfaceName = String.format("indi.uhyils.service.%s", interfaceName);
        }

        ReferenceConfig<GenericService> reference;
        if (map.keySet().contains(interfaceName)) {
            reference = map.get(interfaceName);
            if (reference == null) {
                map.remove(interfaceName);
                reference = getGenericServiceReferenceConfig(interfaceName);
                map.put(interfaceName, reference);
            }
        } else {
            reference = getGenericServiceReferenceConfig(interfaceName);
            map.put(interfaceName, reference);
        }

        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();

        /**
         * GenericService 这个接口只有一个方法，名为 $invoke，它接受三个参数，分别为方法名、方法参数类型数组和参数值数组；
         * 对于方法参数类型数组 如果是基本类型，如 int 或 long，可以使用 int.class.getName()获取其类型； 如果是基本类型数组，如
         * int[]，则可以使用 int[].class.getName()； 如果是 POJO，则直接使用全类名，如
         * com.alibaba.dubbo.samples.generic.api.Params。
         */

        //全部方法
        Method[] methods = Class.forName(interfaceName).getMethods();

        Method method = Arrays.stream(methods).filter(m -> methodName.equalsIgnoreCase(m.getName()) && args.size() == m.getParameterTypes().length).findFirst().get();

        Class[] params = method.getParameterTypes();

        List<String> paramNameList = Arrays.stream(params).map(p -> p.getName()).collect(Collectors.toList());

        Object[] arg = args.toArray(new Object[args.size()]);
        String[] parameterTypes = paramNameList.toArray(new String[paramNameList.size()]);
        return JSONObject.parseObject(JSONObject.toJSONString(genericService.$invoke(methodName, parameterTypes, arg)), ServiceResult.class);

    }

    private static ReferenceConfig<GenericService> getGenericServiceReferenceConfig(String interfaceName) {
        ReferenceConfig<GenericService> reference;
        reference = new ReferenceConfig<>();
        // 弱类型接口名
        reference.setInterface(interfaceName);
        // 声明为泛化接口
        reference.setGeneric(true);
        reference.setApplication(SpringUtil.getBean(ApplicationConfig.class));
        reference.setRegistry(SpringUtil.getBean(RegistryConfig.class));
        reference.setConsumer(SpringUtil.getBean(ConsumerConfig.class));
        return reference;
    }

}
