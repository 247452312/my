package indi.uhyils.redis.config;

import indi.uhyils.redis.aop.HotSpotAop;
import indi.uhyils.redis.druid.filter.TableSqlFilter;
import indi.uhyils.redis.hotspot.HotSpotRedisPool;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月11日 08时55分
 */
@Configuration
@Import({
            HotSpotAop.class,
            HotSpotRedisPool.class,
            TableSqlFilter.class
        })
public class HotSpotConfiguration {

}
