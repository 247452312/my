package indi.uhyils.es.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月11日 11时31分
 */
@Configuration
public class EsConfig {


    private static final String HOST = "host";

    private static final String PORT = "port";

    private static final String SCHEME = "scheme";

    /**
     * 单例
     */
    private static final Integer SINGLE_INDEX = 1;


    @Value("${spring.es}")
    HashMap<String, Object> map;

    @Bean
    public RestHighLevelClient createClient() {
        if (map == null) {
            throw new RuntimeException("es没有找到配置");
        }
        HttpHost[] hosts;
        if (map.containsKey(HOST) || map.containsKey(PORT) || map.containsKey(SCHEME)) {
            hosts = new HttpHost[SINGLE_INDEX];
            hosts[0] = new HttpHost(map.get(HOST).toString(), (Integer) map.get(PORT), map.get(SCHEME).toString());
        } else {
            hosts = new HttpHost[map.size()];
            int index = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                HashMap<String, Object> value = (HashMap<String, Object>) entry.getValue();
                Object host = value.get(HOST);
                if (host == null) {
                    throw new RuntimeException("es配置文件中需要有spring.es.es节点名称.host (0.0.0.0)");
                }
                Object port = value.get(PORT);
                if (port == null) {
                    throw new RuntimeException("es配置文件中需要有spring.es.es节点名称.port (1234)");
                }
                Object scheme = value.get(SCHEME);
                if (scheme == null) {
                    throw new RuntimeException("es配置文件中需要有spring.es.es节点名称.scheme (http or https)");
                }
                hosts[index++] = new HttpHost(host.toString(), (Integer) port, scheme.toString());
            }
        }
        return new RestHighLevelClient(RestClient.builder(hosts));
    }
}
