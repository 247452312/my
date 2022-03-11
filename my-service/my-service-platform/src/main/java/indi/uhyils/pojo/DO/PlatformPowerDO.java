package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 权限表(PlatformPower)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@TableName(value = "sys_platform_power")
public class PlatformPowerDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 用户id
     */
    @TableField
    private Long userId;
    /**
     * 发布节点id
     */
    @TableField
    private Long publishNodeId;
    /**
     * 0->申请中 1->使用中 2->已停止
     */
    @TableField
    private Integer status;

    
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }
    
    
    
    
    
    
    public void setPublishNodeId(Long publishNodeId){
        this.publishNodeId = publishNodeId;
    }

    public Long getPublishNodeId(){
        return publishNodeId;
    }
    
    
    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return status;
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
