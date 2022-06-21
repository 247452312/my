package indi.uhyils.context;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月21日 08时26分
 */
public interface SpiderContext {


    /**
     * 黑名单 在redis中的key
     *
     * @ps 在redis中 黑名单是set格式的
     */
    String IP_BLACK_REDIS_KEY = "ip_black_redis_key";

    /**
     * 临时冻结等级 在redis中的key
     *
     * @ps hash结构 key为ip value为冻结等级
     */
    String IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY = "ip_black_temp_frozen_level_redis_key";

    /**
     * 临时冻结到期时间 在redis中的key
     *
     * @ps hash结构 key为ip value为冻结开始时间
     */
    String IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY = "ip_black_temp_frozen_time_out_redis_key";

    /**
     * 错误次数
     *
     * @ps hash结构 key:ip,value:count
     */
    String IP_TEMP_RECORD_KEY_COUNT = "ip_temp_record_key_count";

    /**
     * 最大错误次数
     */
    Integer WRONG_COUNT = 3;

    /**
     * 首次被冻结时间
     */
    Long FIRST_FROZEN_TIME = 3 * 60 * 1000L;

    /**
     * 第二次冻结时间
     */
    Long SECOND_FROZEN_TIME = 60 * 60 * 1000L;

    /**
     * 最大冻结次数 如果第三次冻结,则清除冻结次数并加入永久黑名单
     */
    Long MAX_FROZEN_COUNT = 2L;


}
