package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时08分
 */
public class TaskClassSwaggerDTO extends ClassSwaggerDTO {

    @ApiModelProperty("方法")
    private MethodSwaggerDTO method;

    public MethodSwaggerDTO getMethod() {
        return method;
    }

    public void setMethod(MethodSwaggerDTO method) {
        this.method = method;
    }
}
