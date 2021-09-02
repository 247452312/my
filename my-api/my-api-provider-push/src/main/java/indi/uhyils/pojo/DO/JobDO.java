package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.UserDTO;

/**
 * 定时任务
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时23分
 */
public class JobDO extends BaseDO {

    /**
     * cron 表达式
     */
    private String cron;

    /**
     * 定时任务名称
     */
    private String name;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数
     */
    private String params;

    /**
     * 是否暂停
     */
    private Boolean pause;

    /**
     * 不计入数据库,只在执行任务时有效
     */
    private UserDTO userEntity;


    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Boolean getPause() {
        return pause;
    }

    public void setPause(Boolean pause) {
        this.pause = pause;
    }

    public UserDTO getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserDTO userEntity) {
        this.userEntity = userEntity;
    }
}
