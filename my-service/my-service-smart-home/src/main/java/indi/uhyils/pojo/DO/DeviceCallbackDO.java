package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * 设备回调表(DeviceCallback)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分16秒
 */
public class DeviceCallbackDO extends BaseDoDO {

    private static final long serialVersionUID = 527480449440624389L;


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
