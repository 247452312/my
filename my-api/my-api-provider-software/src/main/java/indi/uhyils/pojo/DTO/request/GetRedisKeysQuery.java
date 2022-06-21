package indi.uhyils.pojo.DTO.request;


import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * 获取redis指定仓库的值
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 12时18分
 */
public class GetRedisKeysQuery extends DefaultCQE {

    /**
     * redis仓库名称
     */
    private Integer db;

    /**
     * redis Id
     */
    private Long redisId;

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    public Long getRedisId() {
        return redisId;
    }

    public void setRedisId(Long redisId) {
        this.redisId = redisId;
    }
}
