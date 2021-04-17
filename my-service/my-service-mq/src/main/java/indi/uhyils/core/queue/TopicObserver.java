package indi.uhyils.core.queue;

import indi.uhyils.core.topic.Topic;

/**
 * topic观察者
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月15日 08时41分
 * @Version 1.0
 */
public interface TopicObserver {
    /**
     * 观察者方法
     *
     * @return
     */
    Topic getTopic();
}
