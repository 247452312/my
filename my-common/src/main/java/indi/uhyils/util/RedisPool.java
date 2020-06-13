package indi.uhyils.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月04日 11时53分
 */
@Lazy
@Component
public class RedisPool {

    /**
     * jedis连接池
     */
    private static JedisPool pool;
    /**
     * 最大连接数
     */
    private static Integer maxTotal = 8;
    /**
     * 在jedispool中最大的idle状态(空闲的)的jedis实例的个数
     */
    private static Integer maxIdle = 4;
    /**
     * 在jedispool中最小的idle状态(空闲的)的jedis实例的个数
     */
    private static Integer minIdle = 2;
    /**
     * 在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
     */
    private static Boolean testOnBorrow = false;
    /**
     * 在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。
     */
    private static Boolean testOnReturn = false;
    @Value("${redis.ip}")
    private String redisIp;
    @Value("${redis.port}")
    private String redisPort;


    private void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        config.setBlockWhenExhausted(true);
        pool = new JedisPool(config, redisIp, Integer.parseInt(redisPort), 1000 * 2);
    }

    public Jedis getJedis() {
        if (pool == null) {
            initPool();
        }
        return pool.getResource();
    }

}
