package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 接口降级策略(Relegation)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分21秒
 */
@TableName(value = "sys_relegation")
public class RelegationDO extends BaseDO {

    private static final long serialVersionUID = -33807962886310261L;


    /**
     * 接口名称
     */
    @TableField
    private String serviceName;

    /**
     * 方法名称
     */
    @TableField
    private String methodName;

    /**
     * 入参个数
     */
    @TableField
    private Integer paramLength;

    /**
     * 服务等级
     */
    @TableField
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
