package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 用户(User)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 08时53分19秒
 */
@TableName(value = "sys_user")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 700505590518997236L;


    /**
     * 昵称
     */
    @TableField
    private String nickName;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * 角色id
     */
    @TableField
    private Long roleId;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 邮箱
     */
    @TableField
    private String mail;

    /**
     * 电话
     */
    @TableField
    private String phone;

    /**
     * 头像
     */
    @TableField
    private String headPortrait;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
