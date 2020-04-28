package indi.uhyils.request;

import indi.uhyils.model.UserEntity;

import java.io.Serializable;

/**
 * 请求外包类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时04分
 */
public class DefaultRequest implements Serializable {
    /**
     * token
     */
    private String token;

    private UserEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
