package indi.uhyils.core.topic;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.queue.QueueFactory;


/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时47分
 * @Version 1.0
 */
public class PartitionSequentialTopic extends AbstractTopic {

    /**
     * 此topic根据哪一个key分区
     */
    private final String key;

    public PartitionSequentialTopic(String name, String key) {
        super(name);
        this.key = key;
    }

    public static PartitionSequentialTopic build(String name, String key, PushType pushType, ReceiveType receiveType) {
        PartitionSequentialTopic build = new PartitionSequentialTopic(name, key);
        build.setPushType(pushType);
        build.setReceiveType(receiveType);
        return build;
    }

    @Override
    public void setPushType(PushType pushType) {
        this.pushType = pushType;
    }

    @Override
    public void setReceiveType(ReceiveType receiveType) {
        this.receiveType = receiveType;
    }

    @Override
    protected Boolean saveMessage0(Message message) {
        Queue markQueue;
        JSONObject data = message.getData();
        Object keyObject = data.getOrDefault(this.key, "null");
        // 如果之前队列存在
        if (getQueues().containsKey(keyObject.toString())) {
            markQueue = getQueues().get(keyObject.toString());
        } else {
            markQueue = QueueFactory.createNormalQueue(this);
            getQueues().put(keyObject.toString(), markQueue);
        }
        return markQueue.saveMessage(message);
    }

    @Override
    public TopicType getTopicType() {
        return TopicType.PARTITION_ORDER_MSG;
    }

}
