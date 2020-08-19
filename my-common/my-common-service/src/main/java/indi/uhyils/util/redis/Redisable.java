package indi.uhyils.util.redis;

import java.util.List;

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

    /**
     * 查询集合中是否存在某个值
     *
     * @param key    集合名称
     * @param member 值名称
     * @return 是否存在
     */
    Boolean sismember(String key, String member);

    /**
     * 向key所在的集合中添加key
     *
     * @param key     集合名称
     * @param members 元素名称
     * @return 删除个数
     */
    Long sadd(String key, String... members);


    /**
     * 删除集合中的元素
     *
     * @param key     集合名称
     * @param members 元素名称
     * @return 删除个数
     */
    Long srem(String key, String... members);


    /**
     * 查询集合中的个数
     *
     * @param key 集合名称
     * @return 个数
     */
    Long scard(String key);

    /**
     * 执行lua脚本
     *
     * @param lua  lua代码
     * @param keys lua中的key参数
     * @param args lua中的args参数
     * @return
     */
    Object lua(String lua, List<String> keys, List<String> args);


}
