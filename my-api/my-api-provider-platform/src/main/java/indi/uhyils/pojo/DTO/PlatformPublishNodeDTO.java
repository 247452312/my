package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 发布节点表表(PlatformPublishNode)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月21日 18时26分
 */
public class PlatformPublishNodeDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 节点类型 1->mysql 2->oracle(db)
     */
    private Integer dbType;

    /**
     * 请求类型(http)
     */
    private String requestType;

    /**
     * 接口名称(rpc)
     */
    private String interfaceName;

    /**
     * 请求地址(http)
     */
    private String url;

    /**
     * 发布后需要再次数据过滤的低代码,可以没有,如果没有,就不需要再次使用sql进行数据过滤
     */
    private String sql;

    /**
     * 协议类型 1->http协议 2->my-rpc协议 3->db协议 4->mysql协议 等等
     */
    private Integer protocolType;

    /**
     * 节点名称(db)
     */
    private String dbName;

    /**
     * 创建人id
     */
    private Long userId;

    /**
     * 方法名称(rpc)
     */
    private String methodName;

    /**
     * 依据的发布节点id
     */
    private Long nodeId;

    public Integer getDbType() {
        return dbType;
    }

    public void setDbType(Integer dbType) {
        this.dbType = dbType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("dbType", getDbType())
            .append("requestType", getRequestType())
            .append("interfaceName", getInterfaceName())
            .append("url", getUrl())
            .append("sql", getSql())
            .append("protocolType", getProtocolType())
            .append("dbName", getDbName())
            .append("userId", getUserId())
            .append("methodName", getMethodName())
            .append("id", getId())
            .append("nodeId", getNodeId())
            .toString();
    }

}
