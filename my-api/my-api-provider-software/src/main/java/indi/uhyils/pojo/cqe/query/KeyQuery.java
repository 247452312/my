package indi.uhyils.pojo.cqe.query;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时50分
 */
public class KeyQuery implements BaseQuery {

    private Long redisId;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getRedisId() {
        return redisId;
    }

    public void setRedisId(Long redisId) {
        this.redisId = redisId;
    }
}
