package indi.uhyils.core.message;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.core.topic.Topic;
import indi.uhyils.enum_.TopicType;
import indi.uhyils.exception.TopicTypeNotFoundException;
import indi.uhyils.exception.UserException;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 23时28分
 * @Version 1.0
 */
public class MessageFactory {
    public static Message createMessage(JSONObject data, String key, Topic topic) throws UserException {
        TopicType topicType = topic.getTopicType();
        Message message;
        switch (topicType) {
            case PUB_SUB:
            case NORMAL_MSG:
                message = new NormalMessage(data, topic.getName());
                break;
            case PARTITION_ORDER_MSG:
                message = new PartitionSequentialMessage(data, topic.getName(), data.get(key).toString());
                break;
            case GLOBAL_SEQUENTIAL_MSG:
                message = new GlobalSequentialMessage(data, topic.getName());
                break;
            default:
                throw new TopicTypeNotFoundException();
        }
        return message;

    }
}
