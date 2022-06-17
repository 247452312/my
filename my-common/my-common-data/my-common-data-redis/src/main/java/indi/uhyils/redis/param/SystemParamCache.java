package indi.uhyils.redis.param;

import indi.uhyils.annotation.NotNull;
import indi.uhyils.redis.RedisPool;
import indi.uhyils.util.SpringUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月17日 14时42分
 */
public class SystemParamCache {


    /**
     * 获取系统参数值,如果没找到  就返回 {@link SystemParamContext#REDIS_PARAM_NULL}
     *
     * @param global
     * @param sysParamEnum
     *
     * @return
     */
    @NotNull
    public static String findValue(Boolean global, SysParamEnum sysParamEnum) {
        final RedisPool redisPool = SpringUtil.getBean(RedisPool.class);
        return Boolean.TRUE.equals(global) ?
            sysParamEnum.findValue(redisPool) :
            sysParamEnum.findUserValue(redisPool);
    }

    /**
     * 获取系统参数值,如果没找到,则返回指定的值
     *
     * @param global
     * @param sysParamEnum
     *
     * @return
     */
    public static String findValue(Boolean global, SysParamEnum sysParamEnum, SystemParamNotFoundCallBack callBack) {
        final RedisPool redisPool = SpringUtil.getBean(RedisPool.class);
        return Boolean.TRUE.equals(global) ?
            sysParamEnum.findValue(redisPool, callBack) :
            sysParamEnum.findUserValue(redisPool, callBack);
    }

    @FunctionalInterface
    public interface SystemParamNotFoundCallBack {

        /**
         * 参数不存在时的回调方法
         *
         * @param redisPool redis连接池
         * @param key       编译之后的参数key
         *
         * @return
         */
        String accept(Long userId, RedisPool redisPool, String key);
    }

}
