package indi.uhyils.mongo;

import indi.uhyils.util.LogUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 15时26分
 */
public class MongoGuardianThread implements Runnable {

    private MongoConnPool pool;

    /**
     * 保持启动
     */
    private volatile Boolean alive;

    /**
     * 睡眠时间 1分钟
     */
    private static final Long SLEEP_TIME = 1000L * 60;

    public MongoGuardianThread(MongoConnPool pool) {
        alive = true;
        this.pool = pool;
    }

    /**
     * 一分钟轮询一次,查询有没有连接没有人用,并且超时了,就把链接销毁
     */
    @Override
    public void run() {
        List<MongoConn> list = pool.getList();
        while (alive) {
            // 如果只有核心线程,则略过
            if (list.size() > pool.getCoreConnSize()) {
                int i = 0;
                Iterator<MongoConn> iterator = list.iterator();
                Long now = System.currentTimeMillis();
                while (iterator.hasNext()) {
                    MongoConn next = iterator.next();
                    // 核心连接不管
                    i++;
                    if (i <= pool.getCoreConnSize()) {
                        continue;
                    }
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

            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                LogUtil.error(this, e);
            }

        }
    }
}
