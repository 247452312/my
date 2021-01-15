package indi.uhyils.rpc.spring;

import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.rpc.registry.Registry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月15日 16时12分
 */
@Configuration
@Import(RpcAutoConfiguration.RpcScannerConfigurer.class)
public class RpcAutoConfiguration {

    /**
     * 注册类的缓存
     */
    private static Map<String, Registry> registryCache = new HashMap<>();
    //todo 这里要实现bean属性注入完毕之后将@RpcReference扫描到并注入


    public static class RpcScannerConfigurer implements BeanFactoryAware, ImportBeanDefinitionRegistrar {

        private BeanFactory beanFactory;

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }

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
    }

}
