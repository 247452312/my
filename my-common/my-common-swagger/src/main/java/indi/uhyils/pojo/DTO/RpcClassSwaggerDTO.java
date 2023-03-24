package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * {
 *     "serviceName": "类全名",
 *     "serviceDesc": "类注释",
 *     "serviceType": {
 *         "code": 1,
 *         "name": "rpc"
 *     },
 *     "methods": [
 *         {
 *             "public": true,
 *             "readWriteMark": {
 *                 "type": "READ", // READ WRITE
 *                 "tables": [
 *                     "table1",
 *                     "table2"
 *                 ],
 *                 "cacheType": "NOT_TYPE" // ROLE_TYPE角色缓存 ALL_TYPE公用缓存 NOT_TYEP不缓存
 *             },
 *             "name": "login",
 *             "returnType": {
 *                 "fields": [
 *                     {
 *                         "name": "message",
 *                         "type": "java.lang.String",
 *                         "simpleType": "string",
 *                         "desc": "参数说明",
 *                         "schema": "", // 提要, 如果是泛型,则此处显示泛型里面的类名称
 *                         "schemaSimple": "", // 简称题要
 *                         "required": true, // 是否必要 returnType恒为true 以@NotNull为准如果没有NotNull则使用@ApiModelProperty中的required字段
 *                         "childTypes": [
 *                             {} // 如果是array 或者object,则此处应该有后续 注: 这里同returnType 不是field
 *                         ]
 *                     }
 *                 ],
 *                 "name": "",
 *                 "type": "indi.uhyils.pojo.DTO.ClassSwaggerDTO",
 *                 "simpleType": "ClassSwaggerDTO",
 *                 "schema": "", // 提要, 如果是泛型,则此处显示泛型里面的类名称
 *                 "schemaSimple": "", // 简称题要
 *                 "childTypes": [
 *                     {} // 如果返回值本身是array,则此处应该有后续,且fields为空, 注: 这里同returnType 不是field
 *                 ]
 *             },
 *             "paramTypes": [
 *                 {} // 和returnType一样
 *             ]
 *         }
 *     ]
 * }
 *
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
