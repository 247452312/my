package indi.uhyils.request;

import java.io.Serializable;

/**
 * 默认请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时04分
 */
public class DefaultRequest implements Serializable {
    /**
     * token
     */
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
