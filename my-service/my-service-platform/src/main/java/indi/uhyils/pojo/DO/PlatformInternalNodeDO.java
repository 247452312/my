package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 内部节点表(PlatformInternalNode)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@TableName(value = "sys_platform_internal_node")
public class PlatformInternalNodeDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 是否是子节点
     */
    @TableField
    private Integer level;
    /**
     * 创建人id
     */
    @TableField
    private Long userId;
    /**
     * 对应资源id(叶子结点才存在)
     */
    @TableField
    private Long sourceId;
    /**
     * 本身的低代码
     */
    @TableField
    private String sql;

    
    public void setLevel(Integer level){
        this.level = level;
    }

    public Integer getLevel(){
        return level;
    }
    
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }
    
    
    
    
    public void setSourceId(Long sourceId){
        this.sourceId = sourceId;
    }

    public Long getSourceId(){
        return sourceId;
    }
    
    
    
    
    public void setSql(String sql){
        this.sql = sql;
    }

    public String getSql(){
        return sql;
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
