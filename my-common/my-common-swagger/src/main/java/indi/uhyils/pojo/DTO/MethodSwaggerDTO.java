package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 * {
 *     "publicFlag": true,
 *     "readWriteMark": {
 *         "type": "READ", // READ WRITE
 *         "tables": [
 *             "table1",
 *             "table2"
 *         ],
 *         "cacheType": "NOT_TYPE" // ROLE_TYPE角色缓存 ALL_TYPE公用缓存 NOT_TYEP不缓存
 *     },
 *     "name": "login",
 *     "returnType": {
 *         "fields": [
 *             {
 *                 "name": "message",
 *                 "type": "java.lang.String",
 *                 "simpleType": "string",
 *                 "desc": "参数说明",
 *                 "schema": "", // 提要, 如果是泛型,则此处显示泛型里面的类名称
 *                 "schemaSimple": "", // 简称题要
 *                 "required": true, // 是否必要 returnType恒为true 以@NotNull为准如果没有NotNull则使用@ApiModelProperty中的required字段
 *                 "childTypes": [
 *                     {} // 如果是array 或者object,则此处应该有后续 注: 这里同returnType 不是field
 *                 ]
 *             }
 *         ],
 *         "type": "indi.uhyils.pojo.DTO.ClassSwaggerDTO",
 *         "simpleType": "ClassSwaggerDTO",
 *         "schema": "", // 提要, 如果是泛型,则此处显示泛型里面的类名称
 *         "schemaSimple": "", // 简称题要
 *         "childTypes": [
 *             {} // 如果返回值本身是array,则此处应该有后续,且fields为空, 注: 这里同returnType 不是field
 *         ]
 *     },
 *     "paramTypes": [
 *         {} // 和returnType一样
 *     ]
 * }
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时56分
 */
public class MethodSwaggerDTO implements Serializable {

    @ApiModelProperty("方法是否是公共接口(不需要登录或游客验证)")
    private Boolean publicFlag;

    @ApiModelProperty("接口自动缓存配置")
    private ReadWriteMarkInfo readWriteMark;

    @ApiModelProperty("方法名称")
    private String name;

    @ApiModelProperty("返回值信息")
    private ModelInfoDTO resultType;

    @ApiModelProperty("入参信息")
    private List<ModelInfoDTO> paramTypes;

    public Boolean getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(Boolean publicFlag) {
        this.publicFlag = publicFlag;
    }

    public ReadWriteMarkInfo getReadWriteMark() {
        return readWriteMark;
    }

    public void setReadWriteMark(ReadWriteMarkInfo readWriteMark) {
        this.readWriteMark = readWriteMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelInfoDTO getResultType() {
        return resultType;
    }

    public void setResultType(ModelInfoDTO resultType) {
        this.resultType = resultType;
    }

    public List<ModelInfoDTO> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<ModelInfoDTO> paramTypes) {
        this.paramTypes = paramTypes;
    }
}
