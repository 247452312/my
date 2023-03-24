package indi.uhyils.pojo.DTO;

import indi.uhyils.enums.CacheTypeEnum;
import indi.uhyils.enums.ReadWriteTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 * {
 *     "type": "READ", // READ WRITE
 *     "tables": [
 *         "table1",
 *         "table2"
 *     ],
 *     "cacheType": "NOT_TYPE" // ROLE_TYPE角色缓存 ALL_TYPE公用缓存 NOT_TYEP不缓存
 * }
 *
 * 接口自动配置方法
 * @date 文件创建日期 2023年03月22日 17时49分
 * @author uhyils <247452312@qq.com>
 */
public class ReadWriteMarkInfo implements Serializable {

    @ApiModelProperty("接口类型(读or写)")
    private ReadWriteTypeEnum type;

    @ApiModelProperty("接口涉及的表名称")
    private List<String> tables;

    @ApiModelProperty("缓存范围")
    private CacheTypeEnum cacheType;

    public ReadWriteTypeEnum getType() {
        return type;
    }

    public void setType(ReadWriteTypeEnum type) {
        this.type = type;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public CacheTypeEnum getCacheType() {
        return cacheType;
    }

    public void setCacheType(CacheTypeEnum cacheType) {
        this.cacheType = cacheType;
    }
}
