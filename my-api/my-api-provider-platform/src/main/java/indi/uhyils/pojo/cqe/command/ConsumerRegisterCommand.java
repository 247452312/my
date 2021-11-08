package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import javax.validation.constraints.NotNull;


/**
 * 消费者注册请求
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月19日 09时16分
 */
public class ConsumerRegisterCommand extends AbstractCommand {

    /**
     * 消费者名称
     */
    @NotNull
    private String name;

    /**
     * 责任人电话
     */
    @NotNull
    private String responsibilityTelPhone;

    /**
     * 责任人名称
     */
    @NotNull
    private String responsibilityName;

    /**
     * 角色id
     */
    private Long roleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsibilityTelPhone() {
        return responsibilityTelPhone;
    }

    public void setResponsibilityTelPhone(String responsibilityTelPhone) {
        this.responsibilityTelPhone = responsibilityTelPhone;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
