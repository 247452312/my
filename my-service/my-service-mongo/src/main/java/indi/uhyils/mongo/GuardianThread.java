package indi.uhyils.mongo;

import indi.uhyils.util.LogUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 15时26分
 */
public class GuardianThread implements Runnable {

    private MongoConnPool pool;

    /**
     * 保持启动
     */
    private volatile Boolean alive;

    /**
     * 睡眠时间 1分钟
     */
    private static Long SLEEP_TIME = 1000L * 60 * 1;

    public GuardianThread(MongoConnPool pool) {
        alive = true;
        this.pool = pool;
    }

    @Override
    public void run() {
        List<MongoConn> list = pool.getList();
        while (alive) {
            Iterator<MongoConn> iterator = list.iterator();
            Long now = System.currentTimeMillis();
            while (iterator.hasNext()) {
                MongoConn next = iterator.next();
                synchronized (next) {
                    // 没人用 并且超时
                    if (!next.getHaveUse() && now - next.getCreateTime() > pool.getKeepAliveTime()) {
                        // 置空连接池
                        next.setPool(null);
                        next.close();
                        iterator.remove();
                    }
                }
            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                LogUtil.error(this, e);
            }
        }
    }
}
