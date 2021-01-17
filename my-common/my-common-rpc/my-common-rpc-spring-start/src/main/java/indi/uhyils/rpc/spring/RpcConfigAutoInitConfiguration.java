package indi.uhyils.rpc.spring;

import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.util.LogUtil;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月17日 11时12分
 */
@Configuration
@AutoConfigureBefore(RpcAutoConfiguration.class)
public class RpcConfigAutoInitConfiguration {

    /**
     * 初始化配置类
     *
     * @return
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "rpc")
    public RpcConfig rpcConfig() {
        LogUtil.info("rpcConfig init!!");
        return new RpcConfig();
    }
}
