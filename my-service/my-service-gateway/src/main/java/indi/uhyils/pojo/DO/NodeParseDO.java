package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 转换节点解析表(NodeParse)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@TableName(value = "sys_node_parse")
public class NodeParseDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 转换节点id
     */
    @TableField
    private Long nodeId;
    /**
     * 解析出的参数名称
     */
    @TableField
    private String paramName;
    /**
     * 解析出的参数类型(1-出参 2-入参)
     */
    @TableField
    private Integer paramType;
    /**
     * 上级参数id
     */
    @TableField
    private Long fid;

    
    
    
    
    
    
    
    public void setNodeId(Long nodeId){
        this.nodeId = nodeId;
    }

    public Long getNodeId(){
        return nodeId;
    }
    
    public void setParamName(String paramName){
        this.paramName = paramName;
    }

    public String getParamName(){
        return paramName;
    }
    
    public void setParamType(Integer paramType){
        this.paramType = paramType;
    }

    public Integer getParamType(){
        return paramType;
    }
    
    public void setFid(Long fid){
        this.fid = fid;
    }

    public Long getFid(){
        return fid;
    }
    

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
}
