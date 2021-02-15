package indi.uhyils.redis.hotspot;

import indi.uhyils.content.HotSpotContent;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月04日 11时53分
 */
@Component
public class HotSpotRedisPool {

    /**
     * 初始化类型是否是正常的redis
     */
    public static volatile Boolean initTypeIsRedis = true;
    /**
     * jedis连接池
     */
    private static JedisSentinelPool pool;
    /**
     * 是否初始化的标志 如果此类已经初始化,则表示整个类已经初始化过
     */
    private static Object initMark;
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
    @Value("${redis.hotspot.hosts}")
    private String hosts;

    @Value("${redis.hotspot.password}")
    private String password;
    @Value("${redis.hotspot.sentinels}")
    private String sentinels;


    public Boolean initPool() {
        initMark = new Object();
        try {
            JedisPoolConfig config = new JedisPoolConfig();

            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMinIdle(minIdle);

            config.setTestOnBorrow(testOnBorrow);
            config.setTestOnReturn(testOnReturn);
            //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
            config.setBlockWhenExhausted(true);

            // 哨兵信息
            Set sentinelNodes = new HashSet(Arrays.asList(hosts.split(";")));

            //创建连接池
            //mymaster是我们配置给哨兵的服务名称
            pool = new JedisSentinelPool(sentinels, sentinelNodes, config, password);
            //获取客户端
            Jedis jedis = pool.getResource();
            try {
                // 初始化table
                jedis.hsetnx(HotSpotContent.TABLES_HASH_KEY, HotSpotContent.INIT_TABLE_NAME, "1");
            } finally {
                jedis.close();
            }

            initTypeIsRedis = true;
            return true;
        } catch (Exception e) {
            LogUtil.error(this, "redis热点集群没有在线-" + e.getMessage());
            initTypeIsRedis = false;
            return false;
        }
    }

    public Jedis getJedis() {
        // 如果没有初始化过
        if (initMark == null) {
            synchronized (HotSpotRedisPool.class) {
                if (initMark == null) {
                    // 初始化
                    initPool();
                }
            }
        }
        if (initTypeIsRedis) {
            return pool.getResource();
        }
        return null;
    }

}
