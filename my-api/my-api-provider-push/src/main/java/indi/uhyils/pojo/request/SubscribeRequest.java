package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 订阅
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月30日 13时50分
 */
public class SubscribeRequest extends DefaultRequest {

    /**
     * api群id
     */
    private String apiGroup;

    /**
     * 发送类型
     */
    private Integer sendType;

    /**
     * 邮件发送时间
     */
    private String cron;

    public String getApiGroup() {
        return apiGroup;
    }

    public void setApiGroup(String apiGroup) {
        this.apiGroup = apiGroup;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
