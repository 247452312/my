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
     * 保证请求幂等性, 不会在前一个相同幂等id执行结束前执行方法
     */
    private String idempotentId;


    /**
     * 跟踪请求链路
     */
    private LinkNode<String> requestLink;

    public DefaultRequest(DefaultRequest request) {
        this.token = request.token;
        this.user = request.user;
        this.idempotentId = request.idempotentId;
        this.requestLink = request.requestLink;
    }

    public DefaultRequest() {
    }

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

    public String getIdempotentId() {
        return idempotentId;
    }

    public void setIdempotentId(String idempotentId) {
        this.idempotentId = idempotentId;
    }
}
