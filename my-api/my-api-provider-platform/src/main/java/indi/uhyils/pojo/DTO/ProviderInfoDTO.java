package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 服务提供者表(ProviderInfo)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public class ProviderInfoDTO extends IdDTO {

    private static final long serialVersionUID = -67934159083976185L;


    /**
     * 名称
     */
    private String name;

    /**
     * 唯一标识
     */
    private String uniqueKey;

    /**
     * 责任人电话
     */
    private String responsibilityTelPhone;

    /**
     * 责任人名称
     */
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
