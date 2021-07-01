package indi.uhyils.pojo.request;

import java.io.Serializable;

/**
 * redis key值保存
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 13时37分
 */
public class RedisKeyAndValue implements Serializable {

    private Long redisId;
    /**
     * redis 数据库
     */
    private Integer db;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    public Long getRedisId() {
        return redisId;
    }

    public void setRedisId(Long redisId) {
        this.redisId = redisId;
    }

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
