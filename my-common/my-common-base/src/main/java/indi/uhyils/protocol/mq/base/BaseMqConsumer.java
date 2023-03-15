package indi.uhyils.protocol.mq.base;

import java.util.List;

/**
 * mq consumer标识
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时32分
 */
public interface BaseMqConsumer {

    /**
     * 监听的topic
     *
     * @return
     */
    String topic();

    /**
     * 监听的tag
     *
     * @return
     */
    List<String> tags();

    /**
     * 消费者信息
     *
     * @return
     */
    String group();
}
