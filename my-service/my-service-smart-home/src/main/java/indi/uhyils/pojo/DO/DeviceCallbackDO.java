package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 设备回调表(DeviceCallback)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分19秒
 */
public class DeviceCallbackDO extends BaseDO {

    private static final long serialVersionUID = -44248131058536771L;


    /**
     * 有回调的设备id
     */
    private Long deviceId;

    /**
     * 回调url
     */
    private String url;

    /**
     * 意义
     */
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
