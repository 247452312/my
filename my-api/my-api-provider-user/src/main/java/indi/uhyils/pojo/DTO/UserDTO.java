package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DO.RoleDO;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时32分
 */
public class UserDTO extends IdDTO {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 手机
     */
    private String phone;

    /**
     * 头像->(用户id + fileName) MD5后的16位
     */
    private String headPortrait;

    /**
     * 用户角色
     */
    private Long roleId;

    /**
     * 用户角色(实体)
     */
    private RoleDO role;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public RoleDO getRole() {
        return role;
    }

    public void setRole(RoleDO role) {
        this.role = role;
    }
}
