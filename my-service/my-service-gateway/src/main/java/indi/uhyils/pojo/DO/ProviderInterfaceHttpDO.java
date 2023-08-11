package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http接口子表(ProviderInterfaceHttp)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@TableName(value = "sys_provider_interface_http")
public class ProviderInterfaceHttpDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * http的地址
     */
    @TableField
    private String url;

    /**
     * http类型 1->get 2->post 3->delete 4->update
     */
    @TableField
    private Integer httpType;

    /**
     * 主表id
     */
    @TableField
    private Long fid;
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("url", getUrl())
            .append("httpType", getHttpType())
            .append("fid", getFid())
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

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }
}
