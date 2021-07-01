package indi.uhyils.redis;

import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月04日 11时53分
 */
@Component
public class RedisPool {

    /**
     * jedis连接池
     */
    private static JedisPool pool;
    /**
     * 是否初始化的标志 如果此类已经初始化,则表示整个类已经初始化过
     */
    private static Object initMark;

    /**
     * 初始化类型是否是正常的redis
     */
    private static Boolean initTypeIsRedis = Boolean.TRUE;
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
    private static Boolean testOnBorrow = Boolean.FALSE;
    /**
     * 在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。
     */
    private static Boolean testOnReturn = Boolean.FALSE;
    @Value("${redis.normal.ip}")
    private String redisIp;
    @Value("${redis.normal.port}")
    private String redisPort;

    @Value("${redis.normal.password}")
    private String password;


    private Boolean initPool() {
        initMark = new Object();
        try {
            JedisPoolConfig config = new JedisPoolConfig();

            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMinIdle(minIdle);

            config.setTestOnBorrow(testOnBorrow);
            config.setTestOnReturn(testOnReturn);
            //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
            config.setBlockWhenExhausted(Boolean.TRUE);
            pool = new JedisPool(config, redisIp, Integer.parseInt(redisPort), 1000 * 2, password);
            // 测试是否成功
            Jedis resource = pool.getResource();
            resource.close();
            initTypeIsRedis = Boolean.TRUE;
            return Boolean.TRUE;
        } catch (Exception e) {
            LogUtil.error(this, "redis没有在线,临时启用项目内缓存");
            LogUtil.error(this, e);
            initTypeIsRedis = Boolean.FALSE;
            return Boolean.FALSE;
        }
    }

    public Redisable getJedis() {
        // 如果没有初始化过
        if (initMark == null) {
            // 初始化
            Boolean success = initPool();
            // 是否成功
            if (success) {
                return new MyJedis(pool.getResource());
            } else {
                return SpringUtil.getBean(OffLineJedis.class);
            }
        } else {
            // 初始化是否为正式redis
            if (initTypeIsRedis) {
                return new MyJedis(pool.getResource());
            } else {
                return SpringUtil.getBean(OffLineJedis.class);
            }
        }

    }

}
