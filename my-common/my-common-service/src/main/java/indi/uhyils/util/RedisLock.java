package indi.uhyils.util;

import java.util.concurrent.TimeUnit;

/**
 * redis实现的分布式锁
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月14日 17时26分
 */
public class RedisLock {

    private String lockName;
    private RedisPool pool;

    /**
     * 加锁
     */
    public void lock(String lockName) {
    }

    /**
     * 解锁
     */
    public void unlock(String lockName) {
    }

    public void lockInterruptibly() {

    }

    public boolean tryLock() {
    }

    public boolean tryLock(long time, TimeUnit unit) {
    }
}
