package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 转换节点解析表表(NodeParse)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class NodeParseDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 转换节点id
     */
    private Long nodeId;

    /**
     * 解析出的参数名称
     */
    private String paramName;

    /**
     * 解析出的参数类型(1-出参 2-入参)
     */
    private Integer paramType;

    /**
     * 上级参数id
     */
    private Long fid;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("nodeId", getNodeId())
            .append("paramName", getParamName())
            .append("paramType", getParamType())
            .append("fid", getFid())
            .toString();
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

}
