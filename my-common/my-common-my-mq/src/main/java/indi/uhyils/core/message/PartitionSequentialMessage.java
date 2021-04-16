package indi.uhyils.core.message;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.core.topic.TopicType;

/**
 * 分区顺序消息
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 17时39分
 * @Version 1.0
 */
public class PartitionSequentialMessage extends AbstractMessage {

    /**
     * 分区依赖
     */
    private final Object key;

    public PartitionSequentialMessage(JSONObject data, String topic, String key) {
        super(data, topic);
        this.key = key;
    }

    @Override
    public TopicType getType() {
        return TopicType.GLOBAL_SEQUENTIAL_MSG;
    }

    public Object getKey() {
        return key;
    }
}
