package indi.uhyils.pojo.request;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.topic.TopicType;

import java.io.Serializable;


/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时12分
 * @Version 1.0
 */
public class SendMessageRequest implements Serializable {

    /**
     * 消息
     */
    private Message message;

    /**
     * 主题
     */
    private String topic;

    /**
     * 类型
     */
    private TopicType type;


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public TopicType getType() {
        return type;
    }

    public void setType(TopicType type) {
        this.type = type;
    }
}
