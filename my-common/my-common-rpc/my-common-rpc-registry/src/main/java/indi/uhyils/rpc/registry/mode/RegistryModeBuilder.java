package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.cluster.enums.LoadBalanceEnum;
import indi.uhyils.rpc.config.ProviderConfig;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.exchange.content.MyRpcContent;
import indi.uhyils.rpc.registry.pojo.RegistryMetadata;
import indi.uhyils.rpc.registry.pojo.RegistryMetadataOfInterface;
import indi.uhyils.rpc.registry.pojo.RegistryMetadataOfMethod;
import indi.uhyils.rpc.registry.pojo.RegistryModelInfo;
import indi.uhyils.rpc.registry.pojo.RegistryProviderNecessaryInfo;
import indi.uhyils.util.IpUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月08日 08时50分
 */
public class RegistryModeBuilder {

    private RegistryModeBuilder() {
    }

    /**
     * 初始化注册信息
     *
     * @param clazz
     *
     * @return
     */
    public static RegistryModelInfo initRegistryInfo(Class<?> clazz) {
        RegistryProviderNecessaryInfo necessaryInfo = initRegistryProviderNecessaryInfo(clazz);
        RegistryMetadata metadata = initRegistryMetadata(clazz);
        return RegistryModelInfo.build(necessaryInfo, metadata);
    }

    /**
     * 初始化注册必要条件
     *
     * @param clazz
     *
     * @return
     */
    private static RegistryProviderNecessaryInfo initRegistryProviderNecessaryInfo(Class<?> clazz) {
        RpcConfig config = RpcConfigFactory.getInstance();
        final ProviderConfig provider = config.getProvider();
        Integer port = provider.getPort();
        RegistryProviderNecessaryInfo necessaryInfo = new RegistryProviderNecessaryInfo();
        necessaryInfo.setHost(IpUtil.getIp());
        necessaryInfo.setPort(port);
        necessaryInfo.setRpcVersion(MyRpcContent.VERSION);
        necessaryInfo.setInterfaceName(clazz.getName());
        necessaryInfo.setHealth(true);
        necessaryInfo.setWeight(20d);
        String clusterName = RpcConfigFactory.getInstance().getApplication().getName();
        necessaryInfo.setClusterName(clusterName);
        // 开启优雅上下线之后 注册时不发布服务
        necessaryInfo.setEnable(provider.isElegant());

        return necessaryInfo;
    }

    /**
     * 元数据初始化
     *
     * @param clazz
     *
     * @return
     */
    private static RegistryMetadata initRegistryMetadata(Class<?> clazz) {
        RegistryMetadata metadata = new RegistryMetadata();

        List<RegistryMetadataOfMethod> methodInfos = initMethodInfo(clazz);
        metadata.setMethodInfos(methodInfos);

        RegistryMetadataOfInterface serviceInfo = initInterface();
        metadata.setServiceInfo(serviceInfo);
        return metadata;
    }

    /**
     * 初始化方法信息
     *
     * @param clazz
     *
     * @return
     */
    private static List<RegistryMetadataOfMethod> initMethodInfo(Class<?> clazz) {
        List<RegistryMetadataOfMethod> methodInfos = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            RegistryMetadataOfMethod metadataOfMethod = new RegistryMetadataOfMethod();
            metadataOfMethod.setName(method.getName());
            metadataOfMethod.setMethodParamTypes(Arrays.stream(method.getParameterTypes()).map(Class::getName).toArray(String[]::new));
            metadataOfMethod.setReturnType(method.getReturnType().getName());
            metadataOfMethod.setUseThisBalance(false);
            metadataOfMethod.setLoadBalance(LoadBalanceEnum.RANDOM.getCode());
            methodInfos.add(metadataOfMethod);
        }
        return methodInfos;
    }

    /**
     * 初始化服务信息
     *
     * @return
     */
    private static RegistryMetadataOfInterface initInterface() {
        RpcConfig config = RpcConfigFactory.getInstance();
        RegistryMetadataOfInterface serviceInfo = new RegistryMetadataOfInterface();
        serviceInfo.setServiceName(config.getApplication().getName());
        serviceInfo.setUseThisBalance(false);
        serviceInfo.setLoadBalance(LoadBalanceEnum.RANDOM.getCode());
        return serviceInfo;
    }
}
