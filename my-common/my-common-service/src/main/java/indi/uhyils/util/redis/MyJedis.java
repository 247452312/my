package indi.uhyils.util.redis;

import redis.clients.jedis.Jedis;

/**
 * Redisable的jedis实现 用来在获取jedis时使用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月06日 06时52分
 */
public class MyJedis implements Redisable {
    /**
     * jedis
     */
    private Jedis jedis;

    public MyJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public String set(String key, String value) {
        return jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public Long exists(String... keys) {
        return jedis.exists(keys);
    }

    @Override
    public Boolean exists(String key) {
        return jedis.exists(key);
    }

    @Override
    public Long del(String... keys) {
        return jedis.del(keys);
    }

    @Override
    public Long del(String key) {
        return jedis.del(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedis.expire(key, seconds);
    }

    @Override
    public Long incrBy(String key, long increment) {
        return jedis.incrBy(key, increment);
    }

    @Override
    public Long setnx(String key, String value) {
        return jedis.setnx(key, value);
    }

    @Override
    public void close() {
        jedis.close();
    }


    @Override
    public Long append(String key, String value) {
        return jedis.append(key, value);
    }
}
