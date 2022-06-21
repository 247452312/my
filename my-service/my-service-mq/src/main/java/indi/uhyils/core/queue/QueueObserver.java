package indi.uhyils.core.queue;

/**
 * 队列观察者
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 20时56分
 * @Version 1.0
 */
public interface QueueObserver {

    /**
     * 获取队列
     *
     * @return
     */
    Queue getQueue();
}
