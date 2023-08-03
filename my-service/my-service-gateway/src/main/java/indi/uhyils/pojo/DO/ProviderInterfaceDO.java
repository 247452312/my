package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@TableName(value = "sys_provider_interface")
public class ProviderInterfaceDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * 调用方式 1->http 2->mysql 3->rpc
     *
     * @see InvokeTypeEnum
     */
    @TableField
    private Integer invokeType;

    /**
     * 此节点的库名称
     */
    @TableField("`database`")
    private String database;

    /**
     * 此节点的表名称
     */
    @TableField("`table`")
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
