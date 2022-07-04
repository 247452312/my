package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月11日 15时12分
 */
public class PublishRpcCommand extends AbstractCommand {


    /**
     * 对外的table名称
     */
    private String name;

    /**
     * 结果类型demo 同param_type
     */
    private String returnType;

    /**
     * 请求类型,例如HTTP中的post请求或者get请求
     */
    private String requestType;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 入参类型demo json形式,实际使用时入参需要匹配此规则
     */
    private String paramType;

    /**
     * 方法名称
     */
    private String methodName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
