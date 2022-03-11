package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 发布节点表表(PlatformPublishNode)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformPublishNodeDTO extends IdDTO {

    private static final long serialVersionUID = -1L;
    
    /**
     * 协议类型 1->http协议 2->my-rpc协议 3->db协议 4->mysql协议 等等
     */
    private Integer protocolType;
    /**
     * 创建人id
     */
    private Long userId;
    /**
     * 依据的发布节点id
     */
    private Long nodeId;
    /**
     * 发布后需要再次数据过滤的低代码,可以没有,如果没有,就不需要再次使用sql进行数据过滤
     */
    private String sql;
    
    public void setProtocolType(Integer protocolType) {
            this.protocolType = protocolType;
        }

    public Integer getProtocolType() {
            return protocolType;
        }
        
    public void setUserId(Long userId) {
            this.userId = userId;
        }

    public Long getUserId() {
            return userId;
        }
        
    public void setNodeId(Long nodeId) {
            this.nodeId = nodeId;
        }

    public Long getNodeId() {
            return nodeId;
        }
        
    public void setSql(String sql) {
            this.sql = sql;
        }

    public String getSql() {
            return sql;
        }
        

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("protocolType", getProtocolType())
                .append("userId", getUserId())
                .append("id", getId())
                .append("nodeId", getNodeId())
                .append("sql", getSql())
                .toString();
    }

}
