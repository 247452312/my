package indi.uhyils.redis;

import indi.uhyils.rpc.config.ApplicationConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.util.LogUtil;

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
     * 默认持有锁3分钟
     */
    private Long lockTime = 3 * 60L;

    /**
     * @param lockName 分布式锁名称
     * @param pool     redis线程池
     * @param thread   这里只做名字用, 和获取加锁信息时用,没有其他特别的用处
     */
    public RedisLock(String lockName, RedisPool pool, Thread thread) {
        this.lockName = lockName;
        this.pool = pool;
        this.thread = thread;
        ApplicationConfig application = RpcConfigFactory.getInstance().getApplication();
        this.value = thread.getName() + ":" + application.getName();
        this.lockCountName = lockName + "_count";
    }

    public RedisLock(String lockName, RedisPool pool, Thread thread, Long lockTime) {
        this(lockName, pool, thread);
        this.lockTime = lockTime;
    }


    public RedisLock() {
    }

    public String getLockName() {
        return lockName;
    }

    public RedisPool getPool() {
        return pool;
    }

    public Thread getThread() {
        return thread;
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
        Redisable jedis = pool.getJedis();
        try {
            Long lockCount = jedis.incrBy(lockCountName, -1L);
            if (lockCount == 0) {
                jedis.del(lockName);
                jedis.del(lockCountName);
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
        Redisable jedis = pool.getJedis();
        try {
            // 如果此线程已经获取到这个锁,那么不需要再次获取
            String s = jedis.get(lockName);
            if (s != null && s.equals(value)) {
                // (因为redis为原子操作 所以不需要再次检测)
                jedis.expire(lockName, time.intValue());
                // 此时该线程真正持有了这把锁
                // 重入数量+1
                jedis.incrBy(lockCountName, 1L);
                jedis.expire(lockCountName, time.intValue());
                return true;
            } else if (s == null) {
                // 尝试获取锁
                Long getLockSuccess = jedis.setnx(lockName, value);
                if (getLockSuccess == 1L) {
                    // (因为redis为原子操作 所以不需要再次检测)
                    jedis.expire(lockName, time.intValue());
                    // 此时该线程真正持有了这把锁
                    // 重入数量+1
                    jedis.incrBy(lockCountName, 1L);
                    jedis.expire(lockCountName, time.intValue());
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
