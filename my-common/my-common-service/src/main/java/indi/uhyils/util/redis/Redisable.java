package indi.uhyils.util.redis;

/**
 * 可以当做redis的东西
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月06日 06时48分
 */
public interface Redisable {

    String set(String key, String value);


    String get(String key);


    Long exists(String... keys);


    Boolean exists(String key);

    Long del(String... keys);


    Long del(String key);

    Long expire(String key, int seconds);

    Long incrBy(String key, long increment);

    Long setnx(String key, String value);

    void close();


    Long append(String key, String value);

}
