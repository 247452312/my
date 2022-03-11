package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 资源主表(PlatformSource)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@TableName(value = "sys_platform_source")
public class PlatformSourceDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 组id 自动归组 各个组类型不一致,例如DB类,url一致则为同一组
     */
    @TableField
    private Long groupId;
    /**
     * 对外的table名称
     */
    @TableField
    private String name;
    /**
     * 类型 0->数据库 1->接口
     */
    @TableField
    private Integer type;

    
    public void setGroupId(Long groupId){
        this.groupId = groupId;
    }

    public Long getGroupId(){
        return groupId;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
    
    
    
    
    
    public void setType(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return type;
    }
    
    

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("groupId", getGroupId())
                .append("name", getName())
                .append("id", getId())
                .append("type", getType())
                .toString();
    }
}
