package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;
import indi.uhyils.pojo.request.SubscribeRequest;

/**
 * api订阅类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月27日 17时17分
 */
public class ApiSubscribeEntity extends BaseVoEntity {

    /**
     * 订阅用户id
     */
    private String userId;

    /**
     * 发送类型 邮箱 短信,页面推送
     */
    private Integer type;

    /**
     * api 组的id
     */
    private String apiGroupId;

    /**
     * 时间
     */
    private String cron;

    public static ApiSubscribeEntity build(SubscribeRequest request) {
        ApiSubscribeEntity apiSubscribeEntity = new ApiSubscribeEntity();
        apiSubscribeEntity.setApiGroupId(request.getApiGroup());
        apiSubscribeEntity.setCron(request.getCron());
        apiSubscribeEntity.setType(request.getSendType());
        apiSubscribeEntity.setUserId(request.getUser().getId());
        return apiSubscribeEntity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getApiGroupId() {
        return apiGroupId;
    }

    public void setApiGroupId(String apiGroupId) {
        this.apiGroupId = apiGroupId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
