package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 接口调用监控信息 和{@link LogMonitorEntity}是多对一的关系,记录了每一次的接口调用的信息 拟将此数据库代替log数据库,但是没有解决无法获取用户ip的痛点
 * {@db sys_monitorInterfaceDetailDO}
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时22分
 */
public class LogMonitorInterfaceCallEntity extends BaseVoEntity {

    /**
     * 见{@link LogMonitorEntity}
     */
    private Long fid;

    /**
     * 用户调用的接口名称
     */
    private String interfaceName;

    /**
     * 用户调用的接口方法
     */
    private String methodName;

    /**
     * 此条操作是否达到了用户想要的效果 即只有ServiceCode{@ps 无法link} 为success时此字段才为true
     */
    private Boolean success;

    /**
     * 操作发生时间 时间戳类型
     */
    private Long time;

    /**
     * 执行时间跨度 时间戳类型
     */
    private Long runTime;
    /**
     * 请求大小
     */
    private Integer requestLength;
    /**
     * 返回值大小
     */
    private Integer responseLength;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("            \"fid\":")
                .append(fid);
        if (interfaceName != null) {
            sb.append(",            \"interfaceName\":\"")
                    .append(interfaceName).append('\"');
        }
        if (methodName != null) {
            sb.append(",            \"methodName\":\"")
                    .append(methodName).append('\"');
        }
        if (success != null) {
            sb.append(",            \"success\":")
                    .append(success);
        }
        if (time != null) {
            sb.append(",            \"time\":")
                    .append(time);
        }
        if (runTime != null) {
            sb.append(",            \"runTime\":")
                    .append(runTime);
        }
        if (requestLength != null) {
            sb.append(",            \"requestLength\":")
                    .append(requestLength);
        }
        if (responseLength != null) {
            sb.append(",            \"responseLength\":")
                    .append(responseLength);
        }
        sb.append('}');
        return sb.toString();
    }
}
