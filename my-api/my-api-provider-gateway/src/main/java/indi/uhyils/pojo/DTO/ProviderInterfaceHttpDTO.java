package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http接口子表表(ProviderInterfaceHttp)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceHttpDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * http的地址
     */
    private String url;

    /**
     * http类型 1->get 2->post 3->delete 4->update
     */
    private Integer httpType;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("url", getUrl())
            .append("httpType", getHttpType())
            .toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHttpType() {
        return httpType;
    }

    public void setHttpType(Integer httpType) {
        this.httpType = httpType;
    }

}
