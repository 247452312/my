package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseIdEntity;

/**
 * 接口调用监控信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时22分
 */
public class MonitorInterfaceDetailDO extends BaseIdEntity {

    private String fid;

    private String interfaceName;

    private String methodName;

    private Boolean success;

    /**
     * 此条发送时间
     */
    private Long time;

    /**
     * 执行时间
     */
    private Long runTime;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("            \"fid\":\"")
                .append(fid).append('\"');
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
        sb.append('}');
        return sb.toString();
    }
}
