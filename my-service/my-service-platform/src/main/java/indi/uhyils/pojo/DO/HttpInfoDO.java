package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.enum_.SourceTypeEnum;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * http连接表(HttpInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@TableName(value = "sys_http_info")
public class HttpInfoDO extends SourceInfoDO {

    private static final long serialVersionUID = -11912923576860627L;


    /**
     * url地址
     */
    @TableField
    private String url;

    /**
     * 入参类型自定义规则
     */
    @TableField
    private String paramType;

    /**
     * 结果类型(demo)
     */
    @TableField
    private String returnType;

    /**
     * 请求类型
     */
    @TableField
    private String requestType;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }


    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public SourceTypeEnum getSourceType() {
        return SourceTypeEnum.HTTP;
    }
}
