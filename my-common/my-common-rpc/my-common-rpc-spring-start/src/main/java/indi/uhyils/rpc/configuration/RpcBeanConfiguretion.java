package indi.uhyils.rpc.configuration;

import indi.uhyils.rpc.annotation.MyRpc;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.constant.RpcConstant;
import indi.uhyils.rpc.exception.MyRpcGreaterThanOneException;
import indi.uhyils.rpc.factory.RpcBeanFactory;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * rpc-spring扫描
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月30日 07时16分
 */
@Configuration
@ConditionalOnProperty(prefix = RpcConstant.RPC_PREFIX, name = "enabled", matchIfMissing = true)
public class RpcBeanConfiguretion {

    @Reference
    private ApplicationContext applicationContext;


    @Reference
    private RpcConfig rpcConfig;

    @Bean
    public RpcBeanFactory initBeanFactory() throws Exception {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MyRpc.class);
        if (beansWithAnnotation.size() > 1) {
            throw new MyRpcGreaterThanOneException();
        }
        Object beanObj = null;
        for (Map.Entry<String, Object> objectEntry : beansWithAnnotation.entrySet()) {
            beanObj = objectEntry.getValue();
            break;
        }
        Class<?> mainClass = beanObj.getClass();
        return RpcBeanFactory.getInstance(mainClass);
    }


    @Bean
    public void postProcessBeanDefinitionRegistry(RpcBeanFactory instance) throws Exception {
        BeanDefinitionRegistry parentBeanFactory = (BeanDefinitionRegistry) applicationContext.getParentBeanFactory();

        Map<Class<?>, Object> rpcBeans = instance.getRpcBeans();
        for (Map.Entry<Class<?>, Object> rpcBeanEntity : rpcBeans.entrySet()) {
            Class<?> key = rpcBeanEntity.getKey();
            String simpleName = key.getSimpleName();
            char[] chars = simpleName.toCharArray();
            if (chars[0] >= 'A' && chars[0] <= 'Z') {
                chars[0] -= 32;
            }
            String beanName = String.valueOf(chars);
            parentBeanFactory.registerBeanDefinition(beanName, new RootBeanDefinition(key));
            Object bean = applicationContext.getBean(beanName);
            rpcBeans.put(key, bean);
        }
    }

}
