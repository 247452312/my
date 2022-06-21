package indi.uhyils.redis;

import indi.uhyils.util.LogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 离线时使用的jedis
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月06日 06时58分
 */
@Lazy
@Component
public class OffLineJedis implements Redisable {

    /**
     * 缓存
     */
    private static final Map<String, String> CACHE = new WeakHashMap<>();

    /**
     * 时间
     */
    private static final Map<String, Long> CACHE_TIME = new HashMap<>();

    /**
     * 锁
     */
    public static ReentrantLock lock = new ReentrantLock();

    /**
     * 代表真的redis没有开
     */
    public volatile static Boolean redisStart = Boolean.FALSE;

    static {
        Runnable thread = () -> {
            while (!redisStart) {
                inspectTimeOutField();
            }
        };
        Thread redis = new Thread(thread);
        redis.setName("redis_guardian_thread");
        redis.setDaemon(Boolean.TRUE);
        redis.start();

    }

    private static void inspectTimeOutField() {
        for (Map.Entry<String, Long> objectLongEntry : CACHE_TIME.entrySet()) {
            Object key = objectLongEntry.getKey();
            Long value = objectLongEntry.getValue();
            // 超时了
            if (value <= System.currentTimeMillis()) {
                lock.lock();
                try {
                    CACHE_TIME.remove(key);
                    CACHE.remove(key);
                } catch (Exception e) {
                    LogUtil.error(RedisPoolHandle.class, e);
                } finally {
                    lock.unlock();
                }
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LogUtil.error(RedisPoolHandle.class, e);
        }
    }

    @Override
    public String set(String key, String value) {
        return CACHE.put(key, value);
    }


    @Override
    public String get(String key) {
        return CACHE.get(key);
    }

    @Override
    public Long exists(String... keys) {
        AtomicLong count = new AtomicLong();
        Arrays.stream(keys).forEach(key -> {
            if (CACHE.containsKey(key)) {
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    @Override
    public Boolean exists(String key) {
        return CACHE.containsKey(key);
    }

    @Override
    public Long del(String... keys) {
        AtomicLong count = new AtomicLong();
        Arrays.stream(keys).forEach(key -> {
            if (CACHE.containsKey(key)) {
                CACHE.remove(key);
                count.getAndIncrement();
                if (CACHE_TIME.containsKey(key)) {
                    CACHE_TIME.remove(key);
                }
            }
        });
        return count.get();
    }

    @Override
    public Long del(String key) {
        Boolean b = Boolean.FALSE;
        if (CACHE.containsKey(key)) {
            CACHE.remove(key);
            b = Boolean.TRUE;
            if (CACHE_TIME.containsKey(key)) {
                CACHE_TIME.remove(key);
            }
        }
        if (b) {
            return 1L;
        }
        return 0L;
    }

    @Override
    public Long expire(String key, int seconds) {
        if (CACHE.containsKey(key)) {
            CACHE_TIME.put(key, System.currentTimeMillis() + seconds * 1000);
            return 1L;
        }
        return 0L;
    }

    @Override
    public Long incrBy(String key, long increment) {
        if (CACHE.containsKey(key)) {
            String s = CACHE.get(key);
            Long l = Long.parseLong(s) + increment;
            CACHE.put(key, l.toString());
            return l;
        }
        return null;
    }

    @Override
    public Long setnx(String key, String value) {
        if (CACHE.containsKey(key)) {
            return 0L;
        }
        CACHE.put(key, value);
        return 1L;
    }

    @Override
    public final void close() {
        // 没有任何操作
    }


    @Override
    public Long append(String key, String value) {
        if (CACHE.containsKey(key)) {
            String s = CACHE.get(key);
            String result = s + value;
            CACHE.put(key, result);
            return (long) result.length();
        } else {
            CACHE.put(key, value);
            return (long) value.length();

        }
    }

    /* redis没有启动时 集合所在的方法是没有用的,只是保证不会出错*/

    @Override
    public Boolean sismember(String key, String member) {
        // 一直返回false. 不保证幂等性
        return Boolean.FALSE;
    }

    @Override
    public Long sadd(String key, String... members) {
        return 0L;
    }

    @Override
    public Long srem(String key, String... members) {
        return 0L;
    }

    @Override
    public Long scard(String key) {
        return 0L;
    }

    @Override
    public Object lua(String lua, List<String> keys, List<String> args) {
        return null;
    }

    @Override
    public Long hdel(String key, String hKey) {
        return null;
    }

    @Override
    public Boolean hexists(String key, String hKey) {
        return null;
    }

    @Override
    public String hget(String key, String hKey) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return null;
    }

    @Override
    public Long hincrby(String key, String hKey) {
        return null;
    }

    @Override
    public void hset(String key, String ip, String value) {

    }

    @Override
    public void hset(String key, HashMap<String, String> values) {

    }


}
