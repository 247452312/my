package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.UserRightEntity;

import java.io.Serializable;
import java.util.List;

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


    /**
     * 用户权限
     */
    private List<UserRightEntity> userRightList;

    public static LoginResponse buildLoginFail() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(false);
        return loginResponse;
    }

    /**
     * @param token      token
     * @param userEntity 用户本体
     * @param list       权限
     * @return
     */
    public static LoginResponse buildLoginSuccess(String token, UserEntity userEntity, List<UserRightEntity> list) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setToken(token);
        loginResponse.setUserEntity(userEntity);
        loginResponse.setUserRightList(list);
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

    public List<UserRightEntity> getUserRightList() {
        return userRightList;
    }

    public void setUserRightList(List<UserRightEntity> userRightList) {
        this.userRightList = userRightList;
    }
}
