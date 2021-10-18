package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 接口权限表(PlatformPower)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public class PlatformPowerDTO extends IdDTO {

    private static final long serialVersionUID = 997462545770889926L;


    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 接口id
     */
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
