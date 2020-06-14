package indi.uhyils.pojo.response;

import java.io.Serializable;

/**
 * redis的key列表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 12时09分
 */
public class RedisKeyResponse implements Serializable {

    /**
     * redis key
     */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static RedisKeyResponse build(String key) {
        RedisKeyResponse redisKeyResponse = new RedisKeyResponse();
        redisKeyResponse.setKey(key);
        return redisKeyResponse;
    }
}
