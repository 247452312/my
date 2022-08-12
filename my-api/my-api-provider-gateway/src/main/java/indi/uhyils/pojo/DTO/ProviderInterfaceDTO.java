package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口表,提供方提供的调用方式以及url表(ProviderInterface)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class ProviderInterfaceDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * url
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
