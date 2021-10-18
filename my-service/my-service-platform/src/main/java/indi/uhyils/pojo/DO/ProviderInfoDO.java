package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 服务提供者表(ProviderInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@TableName(value = "sys_provider_info")
public class ProviderInfoDO extends BaseDO {

    private static final long serialVersionUID = -66780142406294277L;


    /**
     * 名称
     */
    @TableField
    private String name;

    /**
     * 唯一标识
     */
    @TableField
    private String uniqueKey;

    /**
     * 责任人电话
     */
    @TableField
    private String responsibilityTelPhone;

    /**
     * 责任人名称
     */
    @TableField
    private String responsibilityName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }


    public String getResponsibilityTelPhone() {
        return responsibilityTelPhone;
    }

    public void setResponsibilityTelPhone(String responsibilityTelPhone) {
        this.responsibilityTelPhone = responsibilityTelPhone;
    }


    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

}
