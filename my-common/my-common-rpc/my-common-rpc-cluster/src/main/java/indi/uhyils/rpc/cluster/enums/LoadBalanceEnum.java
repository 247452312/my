package indi.uhyils.rpc.cluster.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 11时05分
 */
public enum LoadBalanceEnum {
    /**
     * 根据ip
     */
    IP_HASH(0),
    /**
     * 手动分配权重
     */
    MANUAL_ASSIGNMENT(1),
    /**
     * 随机
     */
    RANDOM(2),
    /**
     * 轮询
     */
    POLLING(3),
    /**
     * 最少活跃
     */
    LEAST_ACTIVE(4),
    /**
     * 最快返回速度
     */
    FASTEST_RETURN_SPEED(5);


    /**
     * 负责均衡对应的code
     */
    private Integer code;


    LoadBalanceEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
