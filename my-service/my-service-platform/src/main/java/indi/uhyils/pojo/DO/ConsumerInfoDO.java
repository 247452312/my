package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 服务消费方信息表(ConsumerInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@TableName(value = "sys_consumer_info")
public class ConsumerInfoDO extends BaseDO {

    private static final long serialVersionUID = -82533069581143531L;


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

    /**
     * 角色id
     */
    @TableField
    private Long roleId;

    /**
     * accessKey
     */
    @TableField
    private String accessKey;

    /**
     * secretKey
     */
    @TableField
    private String secretKey;

    /**
     * 消费者名称
     */
    @TableField
    private String name;


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


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
