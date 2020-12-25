package indi.uhyils.rpc.cluster.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 11时05分
 */
public enum LoadBalanceEnum {
    /**
     * 根据ip
     */
    IP_HASH,
    /**
     * 手动分配权重
     */
    MANUAL_ASSIGNMENT,
    /**
     * 随机
     */
    RANDOM,
    /**
     * 轮询
     */
    POLLING,
    /**
     * 最少活跃
     */
    LEAST_ACTIVE,
    /**
     * 最快返回速度
     */
    FASTEST_RETURN_SPEED

}
