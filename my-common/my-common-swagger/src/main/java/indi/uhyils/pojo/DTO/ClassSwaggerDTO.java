package indi.uhyils.pojo.DTO;

import indi.uhyils.enums.ProtocolTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * swagger请求的返回
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时28分
 */
public class ClassSwaggerDTO implements Serializable {

    @ApiModelProperty("类型")
    private ProtocolTypeEnum serviceType;


    @ApiModelProperty("类名称")
    private String name;

    @ApiModelProperty("类注释")
    private String desc;

    public ProtocolTypeEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(ProtocolTypeEnum serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
