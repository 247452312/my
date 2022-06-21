package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 设备回调表(DeviceCallback)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时15分37秒
 */
@TableName(value = "sys_device_callback")
public class DeviceCallbackDO extends BaseDO {

    private static final long serialVersionUID = 606507188685476846L;


    /**
     * 有回调的设备id
     */
    @TableField
    private Long deviceId;

    /**
     * 回调url
     */
    @TableField
    private String url;

    /**
     * 意义
     */
    @TableField
    private String meaning;


    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
