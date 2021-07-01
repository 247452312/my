package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.UserEntity;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月28日 16时56分
 */
public class LoginResponse implements Serializable {

    /**
     * 登录是否成功
     */
    private Boolean success;

    /**
     * 登录成功后的token
     */
    private String token;

    /**
     * 用户
     */
    private UserEntity userEntity;


    public static LoginResponse buildLoginFail() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(Boolean.FALSE);
        return loginResponse;
    }

    /**
     * @param token      token
     * @param userEntity 用户本体
     * @return 登录成功
     */
    public static LoginResponse buildLoginSuccess(String token, UserEntity userEntity) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(Boolean.TRUE);
        loginResponse.setToken(token);
        loginResponse.setUserEntity(userEntity);
        return loginResponse;

    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
