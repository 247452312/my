package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时56分
 */
public class MethodSwaggerDTO implements Serializable {

    @ApiModelProperty("方法携带的注解")
    private List<String> annotations;

    @ApiModelProperty("方法名称")
    private String name;

    @ApiModelProperty("返回值信息")
    private ModelInfoDTO result;

    @ApiModelProperty("入参信息")
    private List<ModelInfoDTO> params;

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelInfoDTO getResult() {
        return result;
    }

    public void setResult(ModelInfoDTO result) {
        this.result = result;
    }

    public List<ModelInfoDTO> getParams() {
        return params;
    }

    public void setParams(List<ModelInfoDTO> params) {
        this.params = params;
    }
}
