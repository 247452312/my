package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 接口降级策略(Relegation)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分25秒
 */
public class RelegationDTO extends IdDTO {

    private static final long serialVersionUID = 900866082105369325L;


    /**
     * 接口名称
     */
    private String serviceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 入参个数
     */
    private Integer paramLength;

    /**
     * 服务等级
     */
    private Integer level;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public Integer getParamLength() {
        return paramLength;
    }

    public void setParamLength(Integer paramLength) {
        this.paramLength = paramLength;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
