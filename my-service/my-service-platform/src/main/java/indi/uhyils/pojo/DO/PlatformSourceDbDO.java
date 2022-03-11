package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 数据库资源表(PlatformSourceDb)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@TableName(value = "sys_platform_source_db")
public class PlatformSourceDbDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 密码
     */
    @TableField
    private String password;
    /**
     * 资源主表id
     */
    @TableField
    private Long sourceId;
    /**
     * 数据库类型, 0 mysql 1 oracle
     */
    @TableField
    private Integer type;
    /**
     * 数据库url
     */
    @TableField
    private String url;
    /**
     * 用户名
     */
    @TableField
    private String username;

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
    
    
    
    
    
    public void setSourceId(Long sourceId){
        this.sourceId = sourceId;
    }

    public Long getSourceId(){
        return sourceId;
    }
    
    
    
    public void setType(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return type;
    }
    
    
    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
    

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("password", getPassword())
                .append("id", getId())
                .append("sourceId", getSourceId())
                .append("type", getType())
                .append("url", getUrl())
                .append("username", getUsername())
                .toString();
    }
}
