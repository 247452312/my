package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中间节点与外部节点关联关系(NodeLink)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@TableName(value = "sys_node_link")
public class NodeLinkDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * 原始节点id
     */
    @TableField
    private Long originalNodeId;

    /**
     * 目标节点id
     */
    @TableField
    private Long targetNodeId;

    /**
     * 连接类型 1->中间节点连接中间节点 2->中间节点连接外部节点
     */
    @TableField
    private Integer targetNodeType;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("originalNodeId", getOriginalNodeId())
            .append("targetNodeId", getTargetNodeId())
            .append("targetNodeType", getTargetNodeType())
            .toString();
    }

    public Long getOriginalNodeId() {
        return originalNodeId;
    }

    public void setOriginalNodeId(Long originalNodeId) {
        this.originalNodeId = originalNodeId;
    }

    public Long getTargetNodeId() {
        return targetNodeId;
    }

    public void setTargetNodeId(Long targetNodeId) {
        this.targetNodeId = targetNodeId;
    }

    public Integer getTargetNodeType() {
        return targetNodeType;
    }

    public void setTargetNodeType(Integer targetNodeType) {
        this.targetNodeType = targetNodeType;
    }
}
