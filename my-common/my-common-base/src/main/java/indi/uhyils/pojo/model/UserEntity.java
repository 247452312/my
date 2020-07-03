package indi.uhyils.pojo.model;


import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 用户
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时28分
 */
public class UserEntity extends BaseVoEntity {

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
    private String roleId;

    /**
     * 用户角色(实体)
     */
    private RoleEntity role;


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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
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
}
