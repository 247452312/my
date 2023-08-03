package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * {
 * "fields": [
 * {
 * "name": "message",
 * "type": "java.lang.String",
 * "simpleType": "string",
 * "desc": "参数说明",
 * "schema": "", // 提要, 如果是泛型,则此处显示泛型里面的类名称
 * "schemaSimple": "", // 简称题要
 * "required": true, // 是否必要 returnType恒为true 以@NotNull为准如果没有NotNull则使用@ApiModelProperty中的required字段
 * "childTypes": [
 * {} // 如果是array 或者object,则此处应该有后续 注: 这里同returnType 不是field
 * ]
 * }
 * ],
 * "name": "",
 * "type": "indi.uhyils.pojo.DTO.ClassSwaggerDTO",
 * "simpleType": "ClassSwaggerDTO",
 * "schema": "", // 提要, 如果是泛型,则此处显示泛型里面的类名称
 * "schemaSimple": "", // 简称题要
 * "childTypes": [
 * {} // 如果返回值本身是array,则此处应该有后续,且fields为空, 注: 这里同returnType 不是field
 * ]
 * }
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时59分
 */
public class ModelInfoDTO implements Serializable {


    @ApiModelProperty("字段信息")
    private List<ModelFieldInfoDTO> fields;

    @ApiModelProperty("类型全名")
    private String type;

    @ApiModelProperty("类型名称")
    private String simpleType;

    @ApiModelProperty("名称,如果没有名称,则为空")
    private String name;

    @ApiModelProperty("提要")
    private List<String> schema;

    @ApiModelProperty("提要简称")
    private List<String> schemaSimple;

    @ApiModelProperty("如果是array,此处为 泛型信息")
    private List<ModelInfoDTO> childTypes;

    /**
     * 如果此model是递归无法解析或者在之前重复的类,则需要直接使用之前的class名称即可
     */
    @ApiModelProperty("重复的class名称")
    private String repeatClassName;

    /**
     * 快捷创建
     */
    public static ModelInfoDTO build(String repeatClassName) {
        ModelInfoDTO build = new ModelInfoDTO();
        build.setRepeatClassName(repeatClassName);
        return build;
    }

    public List<ModelFieldInfoDTO> getFields() {
        return fields;
    }

    public void setFields(List<ModelFieldInfoDTO> fields) {
        this.fields = fields;
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

    public List<ModelInfoDTO> getChildTypes() {
        return childTypes;
    }

    public void setChildTypes(List<ModelInfoDTO> childTypes) {
        this.childTypes = childTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepeatClassName() {
        return repeatClassName;
    }

    public void setRepeatClassName(String repeatClassName) {
        this.repeatClassName = repeatClassName;
    }
}
