package indi.uhyils.core.message;

import indi.uhyils.core.topic.TopicType;

/**
 * 普通消息
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 17时13分
 * @Version 1.0
 */
public class NormalMessage extends AbstractMessage {

    public NormalMessage(String topic) {
        super(topic);
    }

    @Override
    public TopicType getType() {
        return TopicType.NORMAL_MSG;
    }
}
