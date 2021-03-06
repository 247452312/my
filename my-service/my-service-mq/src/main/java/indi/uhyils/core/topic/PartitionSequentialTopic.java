package indi.uhyils.core.topic;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.register.Register;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.TopicType;
import indi.uhyils.exception.ExpressionInvalidException;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时47分
 * @Version 1.0
 */
public class PartitionSequentialTopic extends AbstractTopic {

    /**
     * 此topic根据哪一个属性分区
     */
    private final String key;

    public PartitionSequentialTopic(String name, String key) {
        super(name);
        this.key = key;
    }

    public static PartitionSequentialTopic build(String name, String key, OutDealTypeEnum pushType, OutDealTypeEnum receiveType) {
        PartitionSequentialTopic build = new PartitionSequentialTopic(name, key);
        build.setPushType(pushType);
        build.setReceiveType(receiveType);
        return build;
    }

    @Override
    public void setPushType(OutDealTypeEnum pushType) {
        this.pushType = pushType;
    }

    @Override
    public void setReceiveType(OutDealTypeEnum receiveType) {
        this.receiveType = receiveType;
    }

    @Override
    protected Boolean saveMessage0(Message message) throws ExpressionInvalidException {
        Queue markQueue;
        JSONObject data = message.getData();
        // 获取指定参数的对应值
        Object keyValue = data.getOrDefault(this.key, "null");
        // 如果之前队列存在
        if (getQueues().containsKey(keyValue.toString())) {
            markQueue = getQueues().get(keyValue.toString());
        } else {
            markQueue = this.getQueueFactory().createPartitionQueue(this, key);
            for (Register consumer : consumers) {
                markQueue.tryToRegister(consumer);
            }
            getQueues().put(keyValue.toString(), markQueue);
        }
        return markQueue.saveMessage(message);
    }

    @Override
    public TopicType getTopicType() {
        return TopicType.PARTITION_ORDER_MSG;
    }

}
