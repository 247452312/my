package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 动态代码主表(DynamicCode)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@TableName(value = "sys_dynamic_code")
public class DynamicCodeDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 应用标记,每个应用自行配置自己的标记
     */
    @TableField
    private String serviceMark;
    /**
     * 组名称
     */
    @TableField
    private Integer groupId;
    /**
     * 类名称
     */
    @TableField
    private String className;
    /**
     * 文件内容
     */
    @TableField
    private String content;

    
    public void setServiceMark(String serviceMark){
        this.serviceMark = serviceMark;
    }

    public String getServiceMark(){
        return serviceMark;
    }
    
    public void setGroupId(Integer groupId){
        this.groupId = groupId;
    }

    public Integer getGroupId(){
        return groupId;
    }
    
    
    
    
    
    
    public void setClassName(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }
    
    
    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
    

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("serviceMark", getServiceMark())
                .append("groupId", getGroupId())
                .append("id", getId())
                .append("className", getClassName())
                .append("content", getContent())
                .toString();
    }
}
