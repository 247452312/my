package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * swagger请求的返回
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时28分
 */
public class ClassSwaggerDTO implements Serializable {

    @ApiModelProperty("类型编码")
    private Integer typeCode;

    @ApiModelProperty("类型名称")
    private String typeName;

    @ApiModelProperty("类名称")
    private String name;

    @ApiModelProperty("类注释")
    private String desc;

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
