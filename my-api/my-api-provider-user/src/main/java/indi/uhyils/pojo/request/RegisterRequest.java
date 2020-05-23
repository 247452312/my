package indi.uhyils.pojo.request;

import indi.uhyils.enum_.UserTypeEnum;

/**
 * 注册
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月29日 07时48分
 */
public class RegisterRequest extends DefaultRequest {


    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private UserTypeEnum userType;

    /**
     * 昵称
     */
    private String nickName;


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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
