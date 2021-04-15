package indi.uhyils.core.topic;

import indi.uhyils.core.exception.PartitionTopicNoKeyException;
import indi.uhyils.core.exception.TopicTypeNoEqualException;
import indi.uhyils.core.exception.TopicTypeNotFoundException;
import indi.uhyils.core.exception.UserException;
import indi.uhyils.pojo.request.SendMessageRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时14分
 * @Version 1.0
 */
public class TopicFactory {

    private static final Map<String, Topic> topicMap = new ConcurrentHashMap<>();

    /**
     * 根据消息创建或获取一个topic
     *
     * @param request
     * @return
     * @throws UserException
     */
    public static Topic createOrGetTopic(SendMessageRequest request) throws UserException {
        return createOrGetTopic(request.getTopic(), request.getType(), OutDealTypeEnum.PASSIVE, OutDealTypeEnum.PASSIVE, null);
    }


    /**
     * 根据消息创建或获取一个topic
     *
     * @return
     * @throws UserException
     */

    public static Topic createOrGetTopic(String topicName, TopicType userType, String key) throws UserException {
        return createOrGetTopic(topicName, userType, OutDealTypeEnum.PASSIVE, OutDealTypeEnum.PASSIVE, key);
    }

    /**
     * 根据消息和消息接收推送类型创建或获取一个topic
     *
     * @param request
     * @return
     * @throws UserException
     */
    public static Topic createOrGetTopic(SendMessageRequest request, OutDealTypeEnum receiveType, OutDealTypeEnum pushType) throws UserException {
        return createOrGetTopic(request.getTopic(), request.getType(), receiveType, pushType, null);
    }

    public static Topic createOrGetTopic(String topicName, TopicType userType, OutDealTypeEnum receiveType, OutDealTypeEnum pushType, String key) throws UserException {

        if (topicMap.containsKey(topicName)) {
            Topic topic = topicMap.get(topicName);
            TopicType topicType = topic.getTopicType();
            // 判断已存在的主体类型和刚刚发送的主体是否相同
            if (topicType != userType) {
                throw new TopicTypeNoEqualException(userType, topicType);
            }
            return topic;
        }
        // 保证在get之后如果还是没有topic, 再进行判断是否创建报错
        if (userType == TopicType.PARTITION_ORDER_MSG && key == null) {
            throw new PartitionTopicNoKeyException();
        }
        Topic topic = newTopic(topicName, userType, receiveType, pushType, key);
        topicMap.put(topicName, topic);
        return topic;
    }

    private static Topic newTopic(String topic, TopicType type, OutDealTypeEnum receiveType, OutDealTypeEnum pushType, String key) throws UserException {
        switch (type) {
            case NORMAL_MSG:
                return NormalTopic.build(topic, pushType, receiveType);
            case GLOBAL_SEQUENTIAL_MSG:
                return GlobalTopic.build(topic, pushType, receiveType);
            case PARTITION_ORDER_MSG:
                return PartitionSequentialTopic.build(topic, key, pushType, receiveType);
            case PUB_SUB:
                return PubSubTopic.build(topic, pushType, receiveType);
            default:
                throw new TopicTypeNotFoundException();
        }
    }
}
