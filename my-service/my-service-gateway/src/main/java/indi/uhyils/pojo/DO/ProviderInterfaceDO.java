package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月16日 10时28分
 */
@TableName(value = "sys_provider_interface")
public class ProviderInterfaceDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * 库名称
     */
    @TableField
    private String database;

    /**
     * 表名称
     */
    @TableField
    private String name;

    /**
     * 唯一标示(url形式)
     */
    @TableField
    private String url;

    /**
     * 调用方式 1->http 2->mysql 3->rpc
     */
    @TableField
    private Integer invokeType;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("database", getDatabase())
            .append("name", getName())
            .append("url", getUrl())
            .append("invokeType", getInvokeType())
            .toString();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(Integer invokeType) {
        this.invokeType = invokeType;
    }
}
