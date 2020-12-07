package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 获取redis的全部仓库
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 12时26分
 */
public class GetRedisDBRequest extends DefaultRequest {

    /**
     * redis ID
     */
    private Long redisId;

    public Long getRedisId() {
        return redisId;
    }

    public void setRedisId(Long redisId) {
        this.redisId = redisId;
    }
}
