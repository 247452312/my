package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * {
 *     "name": "message",
 *     "type": "java.lang.String",
 *     "simpleType": "string",
 *     "desc": "参数说明",
 *     "schema": "", // 提要, 如果是泛型,则此处显示泛型里面的类名称
 *     "schemaSimple": "", // 简称题要
 *     "required": true, // 是否必要 returnType恒为true 以@NotNull为准如果没有NotNull则使用@ApiModelProperty中的required字段
 *     "childTypes": [
 *         {} // 如果是array 或者object,则此处应该有后续 注: 这里同returnType 不是field
 *     ]
 * }
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 16时03分
 */
public class ModelFieldInfoDTO implements Serializable {

    @ApiModelProperty("字段名称")
    private String name;

    @ApiModelProperty("字段类型")
    private String type;

    @ApiModelProperty("字段类型简称")
    private String simpleType;

    @ApiModelProperty("字段描述")
    private String desc;

    @ApiModelProperty("字段提要, 如果是泛型,则此处显示泛型里面的类名称")
    private List<String> schema;

    @ApiModelProperty("字段提要简称")
    private List<String> schemaSimple;

    @ApiModelProperty("字段是否必要 returnType恒为true 以@NotNull为准如果没有NotNull则使用@ApiModelProperty中的required字段")
    private Boolean required;

    @ApiModelProperty("如果字段是array或者object 则此处应该有后续 注: 这里同ModelInfoDTO 不是ModelFieldInfoDTO")
    private List<ModelInfoDTO> childTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSimpleType() {
        return simpleType;
    }

    public void setSimpleType(String simpleType) {
        this.simpleType = simpleType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getSchema() {
        return schema;
    }

    public void setSchema(List<String> schema) {
        this.schema = schema;
    }

    public List<String> getSchemaSimple() {
        return schemaSimple;
    }

    public void setSchemaSimple(List<String> schemaSimple) {
        this.schemaSimple = schemaSimple;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public List<ModelInfoDTO> getChildTypes() {
        return childTypes;
    }

    public void setChildTypes(List<ModelInfoDTO> childTypes) {
        this.childTypes = childTypes;
    }
}
