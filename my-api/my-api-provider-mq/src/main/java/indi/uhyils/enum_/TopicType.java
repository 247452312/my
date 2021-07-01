package indi.uhyils.enum_;

/**
 * 主题中topic的类型
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 14时06分
 * @Version 1.0
 */
public enum TopicType {
    /**
     * 发布订阅消息
     */
    PUB_SUB("发布订阅消息"),
    /**
     * 普通消息
     */
    NORMAL_MSG("普通消息"),
    /**
     * 全局顺序消息
     */
    GLOBAL_SEQUENTIAL_MSG("全局顺序消息"),

    /**
     * 分区顺序消息
     */
    PARTITION_ORDER_MSG("分区顺序消息");

    /**
     * 中文名称
     */
    private String name;

    TopicType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
