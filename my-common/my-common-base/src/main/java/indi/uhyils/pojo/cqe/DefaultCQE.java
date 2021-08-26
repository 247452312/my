package indi.uhyils.pojo.cqe;

import indi.uhyils.pojo.DO.UserDO;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时23分
 */
public class DefaultCQE implements BaseCQE {
    /**
     * token
     */
    private String token;

    /**
     * 请求时如果携带则代表已经有了,不需要解析token
     */
    private UserDO user;

    /**
     * 保证请求幂等性, 不会在前一个相同幂等id执行结束前执行方法
     * <p>
     * 此标记只是防止请求的超时重发等操作,并不是业务上的幂等,业务上的幂等在业务上实现
     */
    private Long unique;

    public DefaultCQE(DefaultCQE request) {
        this.token = request.token;
        this.user = request.user;
        this.unique = request.unique;
    }

    public DefaultCQE() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public Long getUnique() {
        return unique;
    }

    public void setUnique(Long unique) {
        this.unique = unique;
    }

}
