package indi.uhyils.util.redis;

import indi.uhyils.util.LogUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

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
    private static final Map<String, String> cache = new HashMap<>();

    /**
     * 时间
     */
    private static final Map<String, Long> cacheTime = new HashMap<>();

    /**
     * 锁
     */
    public static ReentrantLock lock = new ReentrantLock();

    /**
     * 代表真的redis没有开
     */
    public volatile static Boolean redisStart = false;

    static {
        Runnable thread = () -> {
            while (!redisStart) {
                for (Map.Entry<String, Long> objectLongEntry : cacheTime.entrySet()) {
                    Object key = objectLongEntry.getKey();
                    Long value = objectLongEntry.getValue();
                    // 超时了
                    if (value <= System.currentTimeMillis()) {
                        lock.lock();
                        try {
                            cacheTime.remove(key);
                            cache.remove(key);
                        } catch (Exception e) {
                            LogUtil.error(RedisPoolUtil.class, e);
                        } finally {
                            lock.unlock();
                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    LogUtil.error(RedisPoolUtil.class, e);
                }
            }
        };
        new Thread(thread).start();

    }

    @Override
    public String set(String key, String value) {
        return cache.put(key, value);
    }


    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public Long exists(String... keys) {
        AtomicLong count = new AtomicLong();
        Arrays.stream(keys).forEach(key -> {
            if (cache.containsKey(key)) {
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    @Override
    public Boolean exists(String key) {
        return cache.containsKey(key);
    }

    @Override
    public Long del(String... keys) {
        AtomicLong count = new AtomicLong();
        Arrays.stream(keys).forEach(key -> {
            if (cache.containsKey(key)) {
                cache.remove(key);
                count.getAndIncrement();
                if (cacheTime.containsKey(key)) {
                    cacheTime.remove(key);
                }
            }
        });
        return count.get();
    }

    @Override
    public Long del(String key) {
        Boolean b = false;
        if (cache.containsKey(key)) {
            cache.remove(key);
            b = true;
            if (cacheTime.containsKey(key)) {
                cacheTime.remove(key);
            }
        }
        if (b) {
            return 1L;
        }
        return 0L;
    }

    @Override
    public Long expire(String key, int seconds) {
        if (cache.containsKey(key)) {
            cacheTime.put(key, System.currentTimeMillis() + seconds * 1000);
            return 1L;
        }
        return 0L;
    }

    @Override
    public Long incrBy(String key, long increment) {
        if (cache.containsKey(key)) {
            String s = cache.get(key);
            Long l = Long.parseLong(s) + increment;
            cache.put(key, l.toString());
            return l;
        }
        return null;
    }

    @Override
    public Long setnx(String key, String value) {
        if (cache.containsKey(key)) {
            return 0L;
        }
        cache.put(key, value);
        return 1L;
    }

    @Override
    public final void close() {
        // 没有任何操作
    }


    @Override
    public Long append(String key, String value) {
        if (cache.containsKey(key)) {
            String s = cache.get(key);
            String result = s + value;
            cache.put(key, result);
            return (long) result.length();
        } else {
            cache.put(key, value);
            return (long) value.length();

        }
    }

    /* redis没有启动时 集合所在的方法是没有用的,只是保证不会出错*/

    @Override
    public Boolean sismember(String key, String member) {
        // 一直返回false. 不保证幂等性
        return false;
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


}
