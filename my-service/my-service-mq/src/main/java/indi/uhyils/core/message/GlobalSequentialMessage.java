package indi.uhyils.core.message;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enums.TopicType;

/**
 * 全局顺序消息
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 17时39分
 * @Version 1.0
 */
public class GlobalSequentialMessage extends AbstractMessage {

    public GlobalSequentialMessage(JSONObject data, String topic) {
        super(data, topic);
    }

    @Override
    public TopicType getType() {
        return TopicType.GLOBAL_SEQUENTIAL_MSG;
    }
}
