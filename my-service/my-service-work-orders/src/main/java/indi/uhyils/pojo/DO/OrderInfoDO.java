package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 工单基础信息样例表(OrderInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时46分05秒
 */
@TableName(value = "sys_order_info")
public class OrderInfoDO extends BaseDO {

    private static final long serialVersionUID = -47058371114257621L;


    /**
     * 工单名称
     */
    @TableField
    private String name;

    /**
     * 工单描述
     */
    @TableField
    private String desc;

    /**
     * 优先级 0->普通 1->加急
     */
    @TableField
    private Integer priority;

    /**
     * 是否是子流程
     */
    @TableField
    private Integer son;

    /**
     * 监管人id
     */
    @TableField
    private Long monitorUserId;

    /**
     * 可查询人id
     */
    @TableField
    private String queryUserIds;

    /**
     * 运行状态
     * 0->停用
     * 1->启用
     * 2->撤回中
     * 3->已撤回
     * 4->停用中
     * 5->回退中
     */
    @TableField
    private Integer status;

    /**
     * 运行时限(分钟)
     */
    @TableField
    private Integer limitTime;

    /**
     * 类型 dict的OrderType
     */
    @TableField
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


    public Integer getSon() {
        return son;
    }

    public void setSon(Integer son) {
        this.son = son;
    }


    public Long getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(Long monitorUserId) {
        this.monitorUserId = monitorUserId;
    }


    public String getQueryUserIds() {
        return queryUserIds;
    }

    public void setQueryUserIds(String queryUserIds) {
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
