package indi.uhyils.pojo.DTO;

import indi.uhyils.annotation.Nullable;
import indi.uhyils.pojo.DTO.base.IdDTO;

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
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型{@link indi.uhyils.enums.UserTypeEnum
     */
    private String userType;

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
     * 状态(0->申请中 1->使用中 2->已停用)
     */
    private Integer status;

    /**
     * 用户登录token
     */
    private String token;

    /**
     * 用户角色
     */
    private Long roleId;

    /**
     * 用户角色(实体)
     */
    private RoleDTO role;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Nullable
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
