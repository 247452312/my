package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * http接口子表(ProviderInterfaceRpc)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@TableName(value = "sys_provider_interface_rpc")
public class ProviderInterfaceRpcDO extends BaseDO {

    private static final long serialVersionUID = -1L;

    /**
     * 接口名称
     */
    @TableField
    private String interfaceName;

    /**
     * 方法名称
     */
    @TableField
    private String methodName;


    /**
     * 主表id
     */
    @TableField
    private Long fid;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("interfaceName", getInterfaceName())
            .append("methodName", getMethodName())
            .append("fid", getFid())
            .toString();
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }
}
