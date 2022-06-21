package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * api订阅表(ApiSubscribe)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时16分23秒
 */
@TableName(value = "sys_api_subscribe")
public class ApiSubscribeDO extends BaseDO {

    private static final long serialVersionUID = 131605272516196413L;


    @TableField
    private Long apiId;

    @TableField
    private String cron;

    @TableField
    private Integer type;

    @TableField
    private Long userId;

    @TableField
    private Long apiGroupId;


    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }


    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getApiGroupId() {
        return apiGroupId;
    }

    public void setApiGroupId(Long apiGroupId) {
        this.apiGroupId = apiGroupId;
    }

}
