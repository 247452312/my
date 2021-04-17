package indi.uhyils.enum_;

/**
 * 对外处理类型(具体解释看每一个枚举的注释)
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月13日 09时02分
 * @Version 1.0
 */
public enum OutDealTypeEnum {

    /**
     * 主动(主动推送消息,主动拉取消息等)
     */
    ACTIVE,
    /**
     * 被动(被动等待拉取消息,被动接受消息)
     */
    PASSIVE
}
