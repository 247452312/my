package indi.uhyils.pojo.model;

import indi.uhyils.enum_.OrderPriorityEnum;
import indi.uhyils.pojo.model.base.BaseDoDO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
public class OrderInfoDO extends BaseDoDO {

    /**
     * 工单名称
     */
    private String name;

    /**
     * 工单描述
     */
    private String desc;

    /**
     * 优先级 {@link OrderPriorityEnum}
     */
    private Integer priority;

    /**
     * 是否是子流程 0->不是 1->是
     */
    private Boolean son;

    /**
     * 监管人id
     */
    private Long monitorUserId;

    /**
     * 可查询人id
     */
    private Long queryUserIds;

    /**
     * 运行状态{@link indi.uhyils.enum_.OrderStatusEnum}
     */
    private Integer status;

    /**
     * 运行时限(分钟)
     */
    private Integer limitTime;

    /**
     * 类型 从 字典中的OrderType中去找
     */
    private Integer type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getSon() {
        return son;
    }

    public void setSon(Boolean son) {
        this.son = son;
    }

    public Long getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(Long monitorUserId) {
        this.monitorUserId = monitorUserId;
    }

    public Long getQueryUserIds() {
        return queryUserIds;
    }

    public void setQueryUserIds(Long queryUserIds) {
        this.queryUserIds = queryUserIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
