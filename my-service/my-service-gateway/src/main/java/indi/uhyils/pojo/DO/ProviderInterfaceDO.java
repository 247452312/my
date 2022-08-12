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
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@TableName(value = "sys_provider_interface")
public class ProviderInterfaceDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * url
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
            .append("url", getUrl())
            .append("invokeType", getInvokeType())
            .toString();
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
