package indi.uhyils.util;

import org.apache.dubbo.config.ApplicationConfig;
import redis.clients.jedis.Jedis;

/**
 * redis实现的分布式锁
 * TODO 分布式计算写完之后一起测试
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月14日 17时26分
 */
public class RedisLock {

    /**
     * 锁名称 = lock_ + 传入的名称
     */
    private String lockName;

    /**
     * 锁的重入数量
     */
    private String lockCountName;
    /**
     * redis线程池
     */
    private RedisPool pool;
    /**
     * 加锁的线程
     */
    private Thread thread;

    /**
     * 这把锁持有时的value
     */
    private String value;

    /**
     * 默认持有锁3秒
     */
    private Long lockTime = 3000L;

    public String getLockName() {
        return lockName;
    }

    public RedisPool getPool() {
        return pool;
    }


    public Thread getThread() {
        return thread;
    }


    public RedisLock(String lockName, RedisPool pool, Thread thread) {
        this.lockName = lockName;
        this.pool = pool;
        this.thread = thread;
        ApplicationConfig bean = SpringUtil.getBean(ApplicationConfig.class);
        this.value = thread.getName() + ":" + bean.getName();
        this.lockCountName = lockName + "_count";
    }

    public RedisLock(String lockName, RedisPool pool, Thread thread, Long lockTime) {
        this(lockName, pool, thread);
        this.lockTime = lockTime;
    }

    public RedisLock() {
    }

    /**
     * 加锁
     */
    public void lock(Long time) {
        while (true) {
            boolean b = tryLock(time);
            if (b) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LogUtil.error(this, e);
            }
        }
    }

    /**
     * 解锁
     */
    public void unlock() {
        Jedis jedis = pool.getJedis();
        try {
            Long lockCount = jedis.incrBy(lockCountName, -1L);
            if (lockCount == 0) {
                jedis.del(lockName);
            }
        } catch (Exception e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }

    }

    /**
     * 暂时没有写
     */
    @Deprecated
    public void lockInterruptibly() {

    }

    public boolean tryLock() {
        return tryLock(lockTime);
    }

    public final boolean tryLock(Long time) {
        Jedis jedis = pool.getJedis();
        try {
            // 尝试获取锁
            Long getLockSuccess = jedis.setnx(lockName, value);
            if (getLockSuccess == 1L) {
                // 双重检测,防止并发(但是redis应该是原子操作,这一步不知道有没有必要) FIXME
                String s = jedis.get(lockName);
                if (value.equals(s)) {
                    jedis.expire(lockName, time.intValue());
                    // 此时该线程真正持有了这把锁
                    // 重入数量+1
                    jedis.incrBy(lockCountName, 1L);
                    return true;
                }

            }
        } catch (Exception e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }
        return false;

    }
}
