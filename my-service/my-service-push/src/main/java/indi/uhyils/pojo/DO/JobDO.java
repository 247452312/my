package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 定时任务表(Job)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时16分23秒
 */
@TableName(value = "sys_job")
public class JobDO extends BaseDO {

    private static final long serialVersionUID = 455132750901452572L;


    @TableField
    private String cron;

    /**
     * 接口名称
     */
    @TableField
    private String interfaceName;

    /**
     * 方法名称
     */
    @TableField
    private String methodName;

    @TableField
    private String name;

    /**
     * 参数类型
     */
    @TableField
    private String paramType;

    /**
     * 参数
     */
    @TableField
    private String params;

    /**
     * 暂停
     */
    @TableField
    private Boolean pause;


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


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
