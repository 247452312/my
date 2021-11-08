package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import javax.validation.constraints.NotNull;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月20日 10时04分
 */
public class RegisterProviderCommand extends AbstractCommand {

    private static final long serialVersionUID = -1L;

    /**
     * 名称
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
}
