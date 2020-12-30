package indi.uhyils.rpc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月30日 07时34分
 */
@Component
@ConfigurationProperties(prefix = "rpc")
public class RpcConfig {
}
