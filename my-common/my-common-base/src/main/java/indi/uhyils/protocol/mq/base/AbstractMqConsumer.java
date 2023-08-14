package indi.uhyils.protocol.mq.base;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import indi.uhyils.annotation.MyMq;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时44分
 */
public abstract class AbstractMqConsumer extends DefaultConsumer implements BaseMqConsumer {

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    protected AbstractMqConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public String topic() {
        MyMq annotation = this.getClass().getAnnotation(MyMq.class);
        return annotation.topic();
    }

    @Override
    public List<String> tags() {
        MyMq annotation = this.getClass().getAnnotation(MyMq.class);
        return Arrays.stream(annotation.tags()).collect(Collectors.toList());
    }

    @Override
    public String group() {
        MyMq annotation = this.getClass().getAnnotation(MyMq.class);
        return annotation.group();
    }
}
