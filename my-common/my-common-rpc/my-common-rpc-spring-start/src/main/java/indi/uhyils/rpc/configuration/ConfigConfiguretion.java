package indi.uhyils.rpc.configuration;

import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.constant.RpcConstant;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月30日 19时07分
 */
@Configuration
@AutoConfigureBefore(RpcBeanConfiguretion.class)
public class ConfigConfiguretion {

    @Reference
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnProperty(prefix = RpcConstant.RPC_PREFIX)
    public RpcConfig initRpcConfig() {
        return new RpcConfig();
    }
}
