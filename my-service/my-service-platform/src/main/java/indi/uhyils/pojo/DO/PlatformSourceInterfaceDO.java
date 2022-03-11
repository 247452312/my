package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* 接口资源表(PlatformSourceInterface)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@TableName(value = "sys_platform_source_interface")
public class PlatformSourceInterfaceDO extends BaseDO {
    
    private static final long serialVersionUID = -1L;

    /**
     * 结果类型demo 同param_type
     */
    @TableField
    private String returnType;
    /**
     * 请求类型,例如HTTP中的post请求或者get请求
     */
    @TableField
    private String requestType;
    /**
     * 资源主表id
     */
    @TableField
    private Long sourceId;
    /**
     * url地址
     */
    @TableField
    private String url;
    /**
     * 入参类型demo json形式,实际使用时入参需要匹配此规则
     */
    @TableField
    private String paramType;

    public void setReturnType(String returnType){
        this.returnType = returnType;
    }

    public String getReturnType(){
        return returnType;
    }
    
    
    public void setRequestType(String requestType){
        this.requestType = requestType;
    }

    public String getRequestType(){
        return requestType;
    }
    
    
    
    
    public void setSourceId(Long sourceId){
        this.sourceId = sourceId;
    }

    public Long getSourceId(){
        return sourceId;
    }
    
    
    
    
    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
    
    public void setParamType(String paramType){
        this.paramType = paramType;
    }

    public String getParamType(){
        return paramType;
    }
    

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("returnType", getReturnType())
                .append("requestType", getRequestType())
                .append("id", getId())
                .append("sourceId", getSourceId())
                .append("url", getUrl())
                .append("paramType", getParamType())
                .toString();
    }
}
