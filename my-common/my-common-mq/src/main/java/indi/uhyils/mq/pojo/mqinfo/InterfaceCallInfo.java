package indi.uhyils.mq.pojo.mqinfo;

import java.io.Serializable;

/**
 * 接口调用信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 08时02分
 */
public class InterfaceCallInfo implements Serializable {

    /**
     * 服务器唯一标示
     */
    private JvmUniqueMark jvmUniqueMark;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 是否执行成功
     */
    private Boolean success;

    /**
     * 方法执行时长
     */
    private Long runTime;

    /**
     * 发送请求时间
     */
    private Long time;

    /**
     * 发送长度
     */
    private Integer requestLength;

    /**
     * 返回长度
     */
    private Integer responseLength;


    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public JvmUniqueMark getJvmUniqueMark() {
        return jvmUniqueMark;
    }

    public void setJvmUniqueMark(JvmUniqueMark jvmUniqueMark) {
        this.jvmUniqueMark = jvmUniqueMark;
    }

    public Integer getRequestLength() {
        return requestLength;
    }

    public void setRequestLength(Integer requestLength) {
        this.requestLength = requestLength;
    }

    public Integer getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(Integer responseLength) {
        this.responseLength = responseLength;
    }
}
