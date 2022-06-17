package indi.uhyils.redis.param;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月17日 15时14分
 */
public class SystemParamContext {

    /**
     * 如果redis中没有,则返回此值
     */
    public static final String REDIS_PARAM_NULL = "$#@!";

    /**
     * 系统参数key模板
     */
    public static final String REDIS_PARAM_KEY_EXAMPLE = "REDIS_PARAM_{userId}_{key}";

    /**
     * 系统参数中全局参数用户id
     */
    public static final Long REDIS_PARAM_SYSTEM_USER_ID = -1L;

    /**
     * userId 的缓存持续时间: 一个小时
     */
    public static final int REDIS_PARAM_EXPIRE_TIME = 60 * 60;

    /**
     * 编译redisKey
     *
     * @param userId
     * @param key
     *
     * @return
     */
    public static String compileRedisKey(Long userId, String key) {
        return REDIS_PARAM_KEY_EXAMPLE.replace("{userId}", userId.toString()).replace("{key}", key);
    }
}
