package indi.uhyils.enums;

import indi.uhyils.pojo.DTO.OrderInfoDTO;
import java.util.function.Function;

/**
 * 订单状态
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时36分
 */
public enum OrderStatusEnum {
    /**
     * 同name
     */
    STOP("停用", 0, "工单已停用", orderInfoDTO -> orderInfoDTO.getId() + "工单已停用,工单优先度:" + OrderPriorityEnum.parse(orderInfoDTO.getPriority()).getName()),
    START("启动", 1, "工单已启用", orderInfoDTO -> orderInfoDTO.getId() + "工单已启用,工单优先度:" + OrderPriorityEnum.parse(orderInfoDTO.getPriority()).getName()),
    WITHDRAWING("撤回中", 2, "工单撤回申请", orderInfoDTO -> orderInfoDTO.getId() + "工单申请撤回,请尽快审批,工单优先度:" + OrderPriorityEnum.parse(orderInfoDTO.getPriority()).getName()),
    WITHDRAWED("已撤回", 3, "工单撤回成功", orderInfoDTO -> orderInfoDTO.getId() + "工单撤回成功,工单优先度:" + OrderPriorityEnum.parse(orderInfoDTO.getPriority()).getName()),
    STOPING("停用中", 4, "工单停用申请", orderInfoDTO -> orderInfoDTO.getId() + "工单申请停用,请尽快审批,工单优先度:" + OrderPriorityEnum.parse(orderInfoDTO.getPriority()).getName()),
    BACKING("回退中", 5, "工单回退申请", orderInfoDTO -> orderInfoDTO.getId() + "工单申请回退,请尽快审批,工单优先度:" + OrderPriorityEnum.parse(orderInfoDTO.getPriority()).getName()),
    CIRCULATION("流转", 6, "工单流转事务提示", orderInfoDTO -> orderInfoDTO.getId() + "工单已转交到你手,审批人通过,请尽快处理,工单优先度:" + OrderPriorityEnum.parse(orderInfoDTO.getPriority()).getName());

    private String name;

    private Integer code;

    /**
     * 应该发送给监控者的消息
     */
    private String title;

    /**
     * 消息
     */
    private Function<OrderInfoDTO, String> msg;

    OrderStatusEnum(String name, Integer code, String title, Function<OrderInfoDTO, String> msg) {
        this.name = name;
        this.code = code;
        this.title = title;
        this.msg = msg;
    }

    public static OrderStatusEnum parse(Integer status) {
        for (OrderStatusEnum value : values()) {
            if (value.getCode().equals(status)) {
                return value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg(OrderInfoDTO orderInfo) {
        return this.msg.apply(orderInfo);
    }
}
