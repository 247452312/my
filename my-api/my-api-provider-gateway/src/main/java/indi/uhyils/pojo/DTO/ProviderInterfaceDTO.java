package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口表,提供方提供的调用方式以及url表(ProviderInterface)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月16日 10时28分
 */
public class ProviderInterfaceDTO extends IdDTO {

    private static final long serialVersionUID = -1L;


    /**
     * 库名称
     */
    private String database;

    /**
     * 表名称
     */
    private String name;

    /**
     * 唯一标示(url形式)
     */
    private String url;

    /**
     * 调用方式 1->http 2->mysql 3->rpc
     */
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

    public void setName(String name) {
        this.name = name;
    }

}
