package indi.uhyils.core.register;

import indi.uhyils.core.message.Message;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;

import java.io.Serializable;

/**
 * 注册者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月15日 19时13分
 */
public interface Register extends Serializable {
    /**
     * 获取注册者的ip
     *
     * @return
     */
    String getIp();

    /**
     * 获取注册者的端口
     *
     * @return
     */
    Integer getPort();

    /**
     * 获取注册者所在的topic
     *
     * @return
     */
    String getTopicName();

    /**
     * 获取注册者类型
     *
     * @return
     */
    RegisterType getRegisterType();

    /**
     * 获取行为类型
     *
     * @return
     */
    OutDealTypeEnum getBehaviorType();

    /**
     * 推送信息
     *
     * @param message
     * @return
     */
    Boolean pushMessage(Message message);


    /**
     * 获取表达式
     *
     * @return
     */
    String getExpression();
}
