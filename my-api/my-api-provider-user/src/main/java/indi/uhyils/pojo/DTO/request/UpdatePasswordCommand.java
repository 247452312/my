package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * 修改密码请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月24日 14时41分
 */
public class UpdatePasswordCommand extends AbstractCommand {


    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
