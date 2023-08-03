package indi.uhyils.pojo.DTO;

import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口表,提供方提供的调用方式以及url表(ProviderInterface)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 调用方式 1->http 2->mysql 3->rpc
     *
     * @see InvokeTypeEnum
     */
    private Integer invokeType;

    /**
     * 此节点的库名称
     */
    private String database;

    /**
     * 此节点的表名称
     */
    private String table;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("invokeType", getInvokeType())
            .append("database", getDatabase())
            .append("table", getTable())
            .toString();
    }

    public Integer getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(Integer invokeType) {
        this.invokeType = invokeType;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

}
