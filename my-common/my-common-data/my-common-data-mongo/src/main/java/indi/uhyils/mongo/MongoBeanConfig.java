package indi.uhyils.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 14时31分
 */
@Configuration
public class MongoBeanConfig {

    @Autowired
    private MongoConfig mongoConfig;

    @Bean
    public MongoDbFactory getMongoDbFactory() {
        return MongoDbFactory.getInstance(mongoConfig);
    }
}
