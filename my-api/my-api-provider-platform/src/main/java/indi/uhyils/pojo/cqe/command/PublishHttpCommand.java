package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月11日 15时06分
 */
public class PublishHttpCommand extends AbstractCommand {

    /**
     * 结果类型demo 同param_type
     */
    private String returnType;

    /**
     * 请求类型,例如HTTP中的post请求或者get请求
     */
    private String requestType;

    /**
     * 资源主表id
     */
    private Long sourceId;

    /**
     * url地址
     */
    private String url;

    /**
     * 入参类型demo json形式,实际使用时入参需要匹配此规则
     */
    private String paramType;

    /**
     * 对外的table名称
     */
    private String name;


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

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
