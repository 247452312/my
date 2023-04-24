package indi.uhyils.rpc.spring;

import indi.uhyils.rpc.annotation.MyRpc;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import java.util.Map;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * 初始化配置类(单独写一个是为了区分先后, 先加载配置
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月17日 11时12分
 */
@Configuration
@Import(RpcAutoConfiguration.class)
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
        RpcConfig rpcConfig = new RpcConfig();
        RpcConfigFactory.setRpcConfig(rpcConfig);
        return rpcConfig;
    }
}