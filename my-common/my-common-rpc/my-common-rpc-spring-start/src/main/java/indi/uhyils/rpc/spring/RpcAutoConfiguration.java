package indi.uhyils.rpc.spring;

import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.ClusterFactory;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.registry.Registry;
import indi.uhyils.rpc.registry.RegistryFactory;
import indi.uhyils.rpc.registry.mode.nacos.RegistryNacosMode;
import indi.uhyils.rpc.spring.util.ClassUtil;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月15日 16时12分
 */
@Configuration
@AutoConfigureAfter(RpcConfigAutoInitConfiguration.class)
public class RpcAutoConfiguration implements BeanFactoryAware, ApplicationContextAware {

    /**
     * bean name
     */
    private static final String RPC_CONFIGURER = "rpcConfigurer";
    /**
     * 只是写在这里. 不知道干什么..
     */
    private static List<Registry<?>> registries;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    /**
     * 初始化扫描器
     *
     * @return
     */
    @Bean(RPC_CONFIGURER)
    public RpcConfigurer createRpcConfigurer() {
        // 获取所有的扫描包
        List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
        RpcConfigurer rpcConfigurer = new RpcConfigurer();
        rpcConfigurer.setBasePackage(StringUtils.collectionToCommaDelimitedString(packages));
        rpcConfigurer.setAnnotationClass(RpcService.class);
        return rpcConfigurer;
    }

    /**
     * 单独初始化这个类的生产者的Cluster
     *
     * @return
     * @throws Exception
     */
    @Bean("providerCluster")
    @DependsOn({"rpcConfig", RPC_CONFIGURER})
    public Cluster createProviderCluster() throws Exception {
        RpcConfig instance = RpcConfigFactory.getInstance();
        if (!instance.getProvider().isEnable()) {
            return null;
        }
        LogUtil.info("Provider Cluster init!!");

        Map<String, Object> springBeans = applicationContext.getBeansWithAnnotation(RpcService.class);
        Map<String, Object> beans = new HashMap<>(springBeans.size());
        for (Map.Entry<String, Object> entity : springBeans.entrySet()) {
            Object value = entity.getValue();
            Class<?> clazz = ClassUtil.getRealClass(value);
            if (!clazz.isInterface()) {
                Class<?>[] interfaces = clazz.getInterfaces();
                clazz = interfaces[0];
            }
            beans.put(clazz.getName(), value);
        }
        Cluster providerCluster = null;
        try {
            providerCluster = ClusterFactory.createDefaultProviderCluster(instance.getProvider().getPort(), beans);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        // 创建生产者的registries
        try {
            registries = new ArrayList<>(beans.size());
            RegistryNacosMode mode = new RegistryNacosMode();
            for (Map.Entry<String, Object> entry : beans.entrySet()) {
                Object bean = entry.getValue();
                Class<?> clazz = ClassUtil.getRealClass(bean);
                if (!clazz.isInterface()) {
                    Class<?>[] interfaces = clazz.getInterfaces();
                    clazz = interfaces[0];
                }
                registries.add(RegistryFactory.createProvider(clazz, mode));

            }
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        return providerCluster;
    }

    /**
     * bean 注入consumer注解扫描
     *
     * @return
     */
    @Bean("indi.uhyils.rpc.spring.RpcConsumerBeanFieldInjectConfiguration")
    @DependsOn("rpcConfig")
    public RpcConsumerBeanFieldInjectConfiguration createRpcConsumerBeanFieldInjectConfiguration() {
        return new RpcConsumerBeanFieldInjectConfiguration();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
