package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 内部节点表表(PlatformInternalNode)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformInternalNodeDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 是否是子节点
     */
    private Integer level;

    /**
     * 创建人id
     */
    private Long userId;

    /**
     * 对应资源id(叶子结点才存在)
     */
    private Long sourceId;

    /**
     * 本身的低代码
     */
    private String sql;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("level", getLevel())
            .append("userId", getUserId())
            .append("id", getId())
            .append("sourceId", getSourceId())
            .append("sql", getSql())
            .toString();
    }

}
