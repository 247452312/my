package indi.uhyils.protocol.task;

/**
 * 定时任务标识
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 16时30分
 */
public interface BaseTask<T, E> {


    /**
     * 执行某个定时任务
     *
     * @param t 定时任务参数
     *
     * @return 定时任务结果
     */
    E executeTask(T t);
}
