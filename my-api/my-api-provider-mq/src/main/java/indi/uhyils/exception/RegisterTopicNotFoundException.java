package indi.uhyils.exception;

import indi.uhyils.enums.OutDealTypeEnum;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月10日 09时02分
 */
public class RegisterTopicNotFoundException extends UserException {

    public RegisterTopicNotFoundException(String topicName, OutDealTypeEnum topicReceiveType, OutDealTypeEnum topicPushType, OutDealTypeEnum registerReceiveType, OutDealTypeEnum registerPushType) {

        super(String.format("您在注册时指定的topic不存在,您指定的topic详情为: 名称:%s, topic接收信息行为:%s(%s), topic推送信息行为:%s(%s)", topicName, topicReceiveType.toString(), registerReceiveType.toString(), topicPushType
            .toString(), registerPushType.toString()));
    }
}
