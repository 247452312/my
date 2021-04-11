package indi.uhyils.core.queue;

import indi.uhyils.core.topic.Topic;

/**
 * 队列工厂
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时13分
 * @Version 1.0
 */
public class QueueFactory {

    public static Queue createNormalQueue(Topic topic) {
        return new NormalQueue(topic);
    }

}
