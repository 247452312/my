package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.UserDTO;
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
    private UserDTO userEntity;


    public static LoginResponse buildLoginFail() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(Boolean.FALSE);
        return loginResponse;
    }

    /**
     * @param token      token
     * @param userEntity 用户本体
     *
     * @return 登录成功
     */
    public static LoginResponse buildLoginSuccess(String token, UserDTO userEntity) {
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

    public UserDTO getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserDTO userEntity) {
        this.userEntity = userEntity;
    }
}
