package indi.uhyils.core.topic;

import indi.uhyils.core.queue.QueueFactory;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.TopicType;
import indi.uhyils.exception.PartitionTopicNoKeyException;
import indi.uhyils.exception.TopicTypeNoEqualException;
import indi.uhyils.exception.TopicTypeNotFoundException;
import indi.uhyils.exception.UserException;
import indi.uhyils.pojo.request.SendMessageRequest;
import indi.uhyils.util.SpringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时14分
 * @Version 1.0
 */
public class TopicFactory {

    private static final Map<String, Topic> TOPIC_MAP = new ConcurrentHashMap<>();

    /**
     * 根据消息创建或获取一个topic
     *
     * @param request
     * @return
     * @throws UserException
     */
    public static Topic createOrGetTopic(SendMessageRequest request) throws UserException {
        return createOrGetTopic(request.getTopic(), request.getType(), OutDealTypeEnum.PASSIVE, OutDealTypeEnum.ACTIVE,
                request.getKey());
    }

    public static Topic getByTopicName(String topicName) {
        return TOPIC_MAP.get(topicName);
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
    public static Topic createOrGetTopic(SendMessageRequest request, OutDealTypeEnum receiveType,
                                         OutDealTypeEnum pushType) throws UserException {
        return createOrGetTopic(request.getTopic(), request.getType(), receiveType, pushType, null);
    }

    /**
     * 创建或者获取一个topic
     *
     * @param topicName   主题的名称
     * @param userType    用户认为的主题的类型
     * @param receiveType 主题接收消息的策略
     * @param pushType    主题分发信息的策略
     * @param key         如果是分区消息,则他的key
     * @return
     * @throws UserException
     */
    public static Topic createOrGetTopic(String topicName, TopicType userType, OutDealTypeEnum receiveType,
                                         OutDealTypeEnum pushType, String key) throws UserException {

        if (TOPIC_MAP.containsKey(topicName)) {
            Topic topic = TOPIC_MAP.get(topicName);
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
        TOPIC_MAP.put(topicName, topic);
        return topic;
    }

    private static Topic newTopic(String topic, TopicType type, OutDealTypeEnum receiveType, OutDealTypeEnum pushType,
                                  String key) throws UserException {
        Topic result;
        switch (type) {
            case NORMAL_MSG:
                result = NormalTopic.build(topic, pushType, receiveType);
                break;
            case GLOBAL_SEQUENTIAL_MSG:
                result = GlobalTopic.build(topic, pushType, receiveType);
                break;
            case PARTITION_ORDER_MSG:
                result = PartitionSequentialTopic.build(topic, key, pushType, receiveType);
                break;
            case PUB_SUB:
                result = PubSubTopic.build(topic, pushType, receiveType);
                break;
            default:
                throw new TopicTypeNotFoundException();
        }
        // 设置默认的队列工厂
        result.setQueueFactory(SpringUtil.getBean(QueueFactory.class));
        return result;
    }
}
