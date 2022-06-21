package indi.uhyils.redis.param;

import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.redis.RedisPool;
import indi.uhyils.redis.Redisable;
import indi.uhyils.redis.param.SystemParamCache.SystemParamNotFoundCallBack;
import java.util.Optional;

/**
 * 参数key
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月17日 14时48分
 */
public enum SysParamEnum {
    /**
     * 意义同desc
     */
    TEMP_USER("temp_user", "临时用户");


    /**
     * 唯一标示
     */
    private final String key;

    /**
     * 描述
     */
    private final String desc;

    SysParamEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * @return
     */
    public static Optional<SysParamEnum> parseEnum(String key) {
        for (SysParamEnum paramEnum : SysParamEnum.values()) {
            if (paramEnum.getKey().equals(key)) {
                return Optional.of(paramEnum);
            }
        }
        return Optional.empty();
    }

    public String getKey() {
        return key;
    }

    /**
     * 刷新缓存
     *
     * @param userId
     * @param value
     * @param pool
     *
     * @return
     */
    public Boolean flush(Long userId, String value, RedisPool pool) {
        final String redisKey = SystemParamContext.compileRedisKey(userId, this.key);
        try (final Redisable jedis = pool.getJedis()) {
            jedis.set(redisKey, value);
            jedis.expire(redisKey, SystemParamContext.REDIS_PARAM_EXPIRE_TIME);
            return true;
        }

    }

    /**
     * 查询参数
     *
     * @param redisPool redis连接池
     * @param callBack  参数不存在时的回调方法
     *
     * @return
     */
    protected String findValue(RedisPool redisPool, SystemParamNotFoundCallBack callBack) {
        return findValue(SystemParamContext.REDIS_PARAM_SYSTEM_USER_ID, redisPool, callBack);
    }

    /**
     * 查询参数
     *
     * @param userId    用户id 如果是-1则代表是全局参数
     * @param redisPool
     * @param callBack
     *
     * @return
     */
    protected String findValue(Long userId, RedisPool redisPool, SystemParamNotFoundCallBack callBack) {
        try (Redisable jedis = redisPool.getJedis()) {
            final String sysRedisKey = SystemParamContext.compileRedisKey(userId, this.key);
            if (jedis.exists(sysRedisKey)) {
                return jedis.get(sysRedisKey);
            }
            return callBack.accept(userId, redisPool, sysRedisKey);
        }
    }

    /**
     * 查询参数
     *
     * @param redisPool
     * @param callBack
     *
     * @return
     */
    protected String findUserValue(RedisPool redisPool, SystemParamNotFoundCallBack callBack) {
        final Optional<UserDTO> userDTO = UserInfoHelper.get();
        if (userDTO.isPresent()) {
            return findValue(userDTO.get().getId(), redisPool, callBack);
        } else {
            return findValue(SystemParamContext.REDIS_PARAM_SYSTEM_USER_ID, redisPool, callBack);
        }
    }

    /**
     * 查询参数
     *
     * @param redisPool
     *
     * @return
     */
    protected String findValue(RedisPool redisPool) {
        return findValue(redisPool, (userId, redisPool1, key1) -> SystemParamContext.REDIS_PARAM_NULL);
    }

    /**
     * 查询参数
     * 逻辑注: 查询用户参数时, 如果没查到,则取查询对应的全局参数, 如果还没查到 就返回{@link SystemParamContext#REDIS_PARAM_NULL}
     *
     * @param redisPool
     *
     * @return
     */
    protected String findUserValue(RedisPool redisPool) {
        return findUserValue(redisPool, (userId, redisPool1, key1) -> findValue(redisPool1, (userId1, redisPool2, key2) -> SystemParamContext.REDIS_PARAM_NULL));
    }

}
