package indi.uhyils.pojo.DTO.request;


import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * 用户登录用request
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月28日 16时49分
 */
public class LoginCommand extends AbstractCommand {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 强制赋权
     */
    private Long roleId;


    public static LoginCommand build(String username, String password, Long roleId) {
        LoginCommand build = new LoginCommand();
        build.setUsername(username);
        build.setPassword(password);
        build.setRoleId(roleId);
        return build;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
