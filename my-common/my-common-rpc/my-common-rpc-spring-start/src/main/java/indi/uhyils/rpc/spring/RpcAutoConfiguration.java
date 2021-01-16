package indi.uhyils.rpc.spring;

import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.ClusterFactory;
import indi.uhyils.rpc.config.ProviderConfig;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.proxy.RpcProxyFactory;
import indi.uhyils.rpc.registry.Registry;
import indi.uhyils.rpc.registry.RegistryFactory;
import indi.uhyils.rpc.registry.exception.RegistryException;
import indi.uhyils.rpc.registry.mode.nacos.RegistryNacosMode;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月15日 16时12分
 */
@Configuration
public class RpcAutoConfiguration implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ApplicationContextAware, InstantiationAwareBeanPostProcessor {

    /**
     * 只是写在这里. 不知道干什么..
     */
    private static List<Registry<?>> registries;
    /**
     * 注册类的缓存
     */
    private volatile static Map<String, Object> consumerRegistryCache = new ConcurrentHashMap<>();
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    /**
     * 初始化配置类
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty("rpc")
    public RpcConfig rpcConfig() {
        return new RpcConfig();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 单独初始化这个类的生产者的Cluster
     *
     * @param rpcConfig
     * @return
     * @throws Exception
     */
    @Bean("providerCluster")
    public Cluster createProviderCluster(RpcConfig rpcConfig) throws Exception {
        ProviderConfig provider = rpcConfig.getProvider();
        if (provider == null) {
            return null;
        }
        Map<String, Object> springBeans = applicationContext.getBeansWithAnnotation(RpcService.class);
        Map<String, Object> beans = new HashMap<>(springBeans.size());
        for (Map.Entry<String, Object> entity : springBeans.entrySet()) {
            beans.put(entity.getValue().getClass().getName(), entity.getValue());
        }
        Cluster providerCluster = ClusterFactory.createDefaultProviderCluster(provider.getPort(), beans);

        registries = new ArrayList<>(beans.size());
        RegistryNacosMode mode = new RegistryNacosMode(rpcConfig.getRegistry().getHost(), rpcConfig.getRegistry().getPort());
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object bean = entry.getValue();
            registries.add(RegistryFactory.createProvider(rpcConfig.getApplication().getName(), bean.getClass(), rpcConfig.getProvider().getPort(), mode));
        }
        return providerCluster;
    }

    /**
     * 通过自定义扫描器将RpcService注解所在的类添加到spring的bean中去
     *
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取所有的扫描包
        List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
        // 新建一个扫描类的builder
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RpcConfigurer.class);
        builder.addPropertyValue("annotationClass", RpcService.class);
        builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(packages));
        //注册这个类
        registry.registerBeanDefinition(RpcConfigurer.class.getName(), builder.getBeanDefinition());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    /**
     * 注入bean中的带有{@link RpcReference}属性或者方法
     *
     * @param bean
     * @param beanName
     * @return
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        RpcConfig rpcConfig = applicationContext.getBean(RpcConfig.class);
        fieldInject(bean, rpcConfig);
        methodInject(bean, rpcConfig);
        return true;
    }

    /**
     * 通过方法注入, 方法入参要同时为远程方法
     *
     * @param bean      类实例
     * @param rpcConfig rpc配置文件
     */
    private void methodInject(Object bean, RpcConfig rpcConfig) {
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
            if (isHaveInitProxy(declaredAnnotations)) {
                try {
                    Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                    for (Class<?> type : parameterTypes) {
                        initConsumerByType(rpcConfig, type);
                    }
                    Object[] params = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {
                        params[i] = consumerRegistryCache.get(parameterTypes[i].getName());
                    }
                    declaredMethod.invoke(bean, params);
                } catch (RegistryException | IllegalAccessException | InvocationTargetException e) {
                    LogUtil.error(this, e);
                }
            }
        }
    }

    /**
     * 通过属性进行注入
     *
     * @param bean      类实例
     * @param rpcConfig 配置
     */
    private void fieldInject(Object bean, RpcConfig rpcConfig) {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            if (isHaveInitProxy(declaredAnnotations)) {
                try {
                    Class<?> type = declaredField.getType();
                    initConsumerByType(rpcConfig, type);
                    declaredField.set(bean, consumerRegistryCache.get(type.getName()));

                } catch (RegistryException | IllegalAccessException e) {
                    LogUtil.error(this, e);
                }
            }
        }
    }

    private boolean isHaveInitProxy(Annotation[] declaredAnnotations) {
        boolean haveInitProxy = false;
        for (Annotation declaredAnnotation : declaredAnnotations) {
            if (declaredAnnotation instanceof RpcReference) {
                haveInitProxy = true;
                break;
            }
        }
        return haveInitProxy;
    }

    private void initConsumerByType(RpcConfig rpcConfig, Class<?> type) throws RegistryException {
        if (consumerRegistryCache.get(type.getName()) == null) {
            synchronized (consumerRegistryCache) {
                if (consumerRegistryCache.get(type.getName()) == null) {
                    Object o = RpcProxyFactory.newProxy(type, rpcConfig.getRegistry().getHost(), rpcConfig.getRegistry().getPort());
                    consumerRegistryCache.put(type.getName(), o);
                }
            }
        }
    }



}
