package indi.uhyils.request;

import indi.uhyils.enum_.UserTypeEnum;

/**
 * 用户登录用request
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月28日 16时49分
 */
public class LoginRequest extends DefaultRequest {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型 0->管理 1->用户 2->商户 3->游客
     */
    private UserTypeEnum userType;


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

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
