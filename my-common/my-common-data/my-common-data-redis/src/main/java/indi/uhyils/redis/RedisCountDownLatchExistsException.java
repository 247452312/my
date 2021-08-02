package indi.uhyils.redis;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月17日 08时28分
 */
public class RedisCountDownLatchExistsException extends Exception {

    public RedisCountDownLatchExistsException(String key) {
        super("分布式countDownLatch已存在:" + key);
    }
}
