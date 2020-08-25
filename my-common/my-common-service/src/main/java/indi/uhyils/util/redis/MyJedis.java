package indi.uhyils.util.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

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

    @Override
    public Boolean sismember(String key, String member) {
        return jedis.sismember(key, member);
    }

    @Override
    public Long sadd(String key, String... members) {
        return jedis.sadd(key, members);
    }

    @Override
    public Long srem(String key, String... members) {
        return jedis.srem(key, members);
    }

    @Override
    public Long scard(String key) {
        return jedis.scard(key);
    }

    @Override
    public Object lua(String lua, List<String> keys, List<String> args) {
        String luaKey = jedis.scriptLoad(lua);
        return jedis.evalsha(luaKey, keys, args);
    }

    @Override
    public Long hdel(String key, String hKey) {
        return jedis.hdel(key, hKey);
    }

    @Override
    public Boolean hexists(String key, String hKey) {
        return jedis.hexists(key, hKey);
    }

    @Override
    public String hget(String key, String hKey) {
        return jedis.hget(key, hKey);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedis.hgetAll(key);
    }

    @Override
    public Long hincrby(String key, String hKey) {
        return jedis.hincrBy(key, hKey, 1L);
    }

    @Override
    public void hset(String key, String ip, String value) {
        jedis.hset(key, ip, value);
    }
}
