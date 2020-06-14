package indi.uhyils.pojo.request;

/**
 * 获取redis指定仓库的值
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 12时18分
 */
public class GetRedisKeysRequest extends DefaultRequest {

    /**
     * redis仓库名称
     */
    private Integer db;

    /**
     * redis Id
     */
    private String redisId;

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    public String getRedisId() {
        return redisId;
    }

    public void setRedisId(String redisId) {
        this.redisId = redisId;
    }
}
