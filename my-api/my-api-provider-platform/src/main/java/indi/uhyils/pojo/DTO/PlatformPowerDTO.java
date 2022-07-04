package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 权限表表(PlatformPower)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformPowerDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 发布节点id
     */
    private Long publishNodeId;

    /**
     * 0->申请中 1->使用中 2->已停止
     */
    private Integer status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPublishNodeId() {
        return publishNodeId;
    }

    public void setPublishNodeId(Long publishNodeId) {
        this.publishNodeId = publishNodeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .append("id", getId())
            .append("publishNodeId", getPublishNodeId())
            .append("status", getStatus())
            .toString();
    }

}
