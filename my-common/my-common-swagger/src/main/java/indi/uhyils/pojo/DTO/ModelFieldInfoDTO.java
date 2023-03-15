package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * model字段的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 16时03分
 */
public class ModelFieldInfoDTO implements Serializable {

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("名称")
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
