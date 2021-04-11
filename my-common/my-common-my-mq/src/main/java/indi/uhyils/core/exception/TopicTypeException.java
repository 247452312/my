package indi.uhyils.core.exception;

import indi.uhyils.core.topic.TopicType;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时19分
 * @Version 1.0
 */
public class TopicTypeException extends UserException {

    public TopicTypeException(TopicType userType, TopicType topicType) {
        super("topic 类型错误,应为" + topicType.getName() + " 实际上为:" + userType.getName());
    }

}
