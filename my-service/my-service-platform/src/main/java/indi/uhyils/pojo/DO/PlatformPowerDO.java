package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 接口权限表(PlatformPower)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@TableName(value = "sys_platform_power")
public class PlatformPowerDO extends BaseDO {

    private static final long serialVersionUID = 841523113808417585L;


    /**
     * 角色id
     */
    @TableField
    private Long roleId;

    /**
     * 接口id
     */
    @TableField
    private Long interfaceId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

}
