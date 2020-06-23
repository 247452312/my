package indi.uhyils.pojo.request.base;

import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.model.LinkNode;

/**
 * 请求父类,所有页面请求都应该继承这个类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时04分
 */
public class DefaultRequest implements BaseRequest {
    /**
     * token
     */
    private String token;

    private UserEntity user;


    /**
     * 跟踪请求链路
     */
    private LinkNode<String> requestLink;

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

    public LinkNode<String> getRequestLink() {
        return requestLink;
    }

    public void setRequestLink(LinkNode<String> requestLink) {
        this.requestLink = requestLink;
    }
}
