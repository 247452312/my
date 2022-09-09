package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@TableName(value = "sys_call_node")
public class CallNodeDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * 厂商id
     */
    @TableField
    private Long companyId;

    /**
     * 转换节点id
     */
    @TableField
    private Long nodeId;

    /**
     * 调用方式 1->http 2->mysql 3->rpc
     */
    @TableField
    private Integer invokeType;

    /**
     * 对应唯一标识(url形式),如果是mysql调用,则使用全称拼写,例如库名/表名
     */
    @TableField
    private String url;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("nodeId", getNodeId())
            .append("invokeType", getInvokeType())
            .append("url", getUrl())
            .toString();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(Integer invokeType) {
        this.invokeType = invokeType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
