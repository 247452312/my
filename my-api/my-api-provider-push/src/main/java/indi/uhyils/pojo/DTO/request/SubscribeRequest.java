package indi.uhyils.pojo.DTO.request;


import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * 订阅
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月30日 13时50分
 */
public class SubscribeRequest extends DefaultCQE {

    /**
     * api群id
     */
    private Long apiGroup;

    /**
     * 发送类型
     */
    private Integer sendType;

    /**
     * 邮件发送时间
     */
    private String cron;

    public Long getApiGroup() {
        return apiGroup;
    }

    public void setApiGroup(Long apiGroup) {
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
