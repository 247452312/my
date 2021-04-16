package indi.uhyils.pojo.request;

import indi.uhyils.core.topic.OutDealTypeEnum;
import java.io.Serializable;

/**
 * 注册一个provider
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 21时17分
 * @Version 1.0
 */
public class RegisterProviderRequest implements Serializable {

    /**
     * topic名称
     */
    private String topicName;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 行为
     */
    private OutDealTypeEnum behavior;

    public OutDealTypeEnum getBehavior() {
        return behavior;
    }

    public void setBehavior(OutDealTypeEnum behavior) {
        this.behavior = behavior;
    }
    
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


}
