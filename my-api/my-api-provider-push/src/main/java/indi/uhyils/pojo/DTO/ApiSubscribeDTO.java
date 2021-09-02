package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.request.SubscribeRequest;

/**
 * api订阅表(ApiSubscribe)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分56秒
 */
public class ApiSubscribeDTO extends IdDTO {

    private static final long serialVersionUID = 829535685068974096L;


    private Long apiId;

    private String cron;

    private Integer type;

    private Long userId;

    private Long apiGroupId;

    public static ApiSubscribeDTO build(SubscribeRequest request) {
        ApiSubscribeDTO apiSubscribeEntity = new ApiSubscribeDTO();
        apiSubscribeEntity.setApiGroupId(request.getApiGroup());
        apiSubscribeEntity.setCron(request.getCron());
        apiSubscribeEntity.setType(request.getSendType());
        apiSubscribeEntity.setUserId(request.getUser().getId());
        return apiSubscribeEntity;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }


    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getApiGroupId() {
        return apiGroupId;
    }

    public void setApiGroupId(Long apiGroupId) {
        this.apiGroupId = apiGroupId;
    }

}
