package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口参数表表(ProviderInterfaceParam)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceParamDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 接口表id
     */
    private Long providerInterfaceId;

    /**
     * 父级参数id
     */
    private Long fid;

    /**
     * 参数类型 1->字符串 2->数值
     */
    private Integer type;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 大小(限制大小或长度)
     */
    private Integer size;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("providerInterfaceId", getProviderInterfaceId())
            .append("fid", getFid())
            .append("type", getType())
            .append("name", getName())
            .append("size", getSize())
            .toString();
    }

    public Long getProviderInterfaceId() {
        return providerInterfaceId;
    }

    public void setProviderInterfaceId(Long providerInterfaceId) {
        this.providerInterfaceId = providerInterfaceId;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
