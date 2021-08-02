package indi.uhyils.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * mongo配置类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 14时31分
 */
@Component
public class MongoConfig {

    /**
     * ip
     */
    @Value("${mongo.ip}")
    private String ip;

    /**
     * 端口
     */
    @Value("${mongo.port}")
    private Integer port;

    /**
     * 用户名
     */
    @Value("${mongo.username}")
    private String username;

    /**
     * 密码
     */
    @Value("${mongo.password}")
    private String password;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
