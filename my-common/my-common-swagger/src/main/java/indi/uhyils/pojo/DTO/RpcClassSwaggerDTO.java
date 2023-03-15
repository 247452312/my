package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时07分
 */
public class RpcClassSwaggerDTO extends ClassSwaggerDTO {

    @ApiModelProperty("方法")
    private List<MethodSwaggerDTO> methods;

    public List<MethodSwaggerDTO> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodSwaggerDTO> methods) {
        this.methods = methods;
    }
}
