package indi.uhyils.exception;

import indi.uhyils.enum_.TopicType;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时19分
 * @Version 1.0
 */
public class TopicTypeNoEqualException extends UserException {

    public TopicTypeNoEqualException(TopicType userType, TopicType topicType) {
        super("topic 类型错误,已存在topic类型为" + topicType.getName() + " 您传递的消息类型为:" + userType.getName());
    }

}
