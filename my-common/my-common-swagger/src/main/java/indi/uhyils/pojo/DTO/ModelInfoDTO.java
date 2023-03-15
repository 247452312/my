package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * model详情
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时59分
 */
public class ModelInfoDTO implements Serializable {

    @ApiModelProperty("model携带的注解")
    private List<String> annotation;

    @ApiModelProperty("model名称")
    private String name;

    @ApiModelProperty("字段信息")
    private List<ModelFieldInfoDTO> fields;

    public List<String> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(List<String> annotation) {
        this.annotation = annotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelFieldInfoDTO> getFields() {
        return fields;
    }

    public void setFields(List<ModelFieldInfoDTO> fields) {
        this.fields = fields;
    }
}
