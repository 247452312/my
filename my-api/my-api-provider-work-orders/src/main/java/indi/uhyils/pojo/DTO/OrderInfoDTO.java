package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;
import java.util.List;

/**
 * 工单基础信息样例表(OrderInfo)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分15秒
 */
public class OrderInfoDTO extends IdDTO {

    private static final long serialVersionUID = -42202023688422058L;


    /**
     * 工单名称
     */
    private String name;

    /**
     * 工单描述
     */
    private String desc;

    /**
     * 优先级 0->普通 1->加急
     */
    private Integer priority;

    /**
     * 是否是子流程
     */
    private Integer son;

    /**
     * 监管人id
     */
    private Long monitorUserId;

    /**
     * 可查询人id
     */
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
    private Integer status;

    /**
     * 运行时限(分钟)
     */
    private Integer limitTime;

    /**
     * 类型 dict的OrderType
     */
    private Integer type;


    /**
     * 节点
     */
    private List<OrderNodeDTO> nodes;


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

    public List<OrderNodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<OrderNodeDTO> nodes) {
        this.nodes = nodes;
    }
}
