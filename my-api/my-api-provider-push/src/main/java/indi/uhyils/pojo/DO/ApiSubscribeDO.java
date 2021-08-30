package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.request.SubscribeRequest;

/**
 * api订阅类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月27日 17时17分
 */
public class ApiSubscribeDO extends BaseDO {

    /**
     * 订阅用户id
     */
    private Long userId;

    /**
     * 发送类型 邮箱 短信,页面推送
     */
    private Integer type;

    /**
     * api 组的id
     */
    private Long apiGroupId;

    /**
     * 时间
     */
    private String cron;

    public static ApiSubscribeDO build(SubscribeRequest request) {
        ApiSubscribeDO apiSubscribeEntity = new ApiSubscribeDO();
        apiSubscribeEntity.setApiGroupId(request.getApiGroup());
        apiSubscribeEntity.setCron(request.getCron());
        apiSubscribeEntity.setType(request.getSendType());
        apiSubscribeEntity.setUserId(request.getUser().getId());
        return apiSubscribeEntity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getApiGroupId() {
        return apiGroupId;
    }

    public void setApiGroupId(Long apiGroupId) {
        this.apiGroupId = apiGroupId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
