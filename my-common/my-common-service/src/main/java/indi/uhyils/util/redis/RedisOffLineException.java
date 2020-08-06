package indi.uhyils.util.redis;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月06日 08时57分
 */
public class RedisOffLineException extends Exception {

    public RedisOffLineException() {
        super("redis不在线!");
    }
}
