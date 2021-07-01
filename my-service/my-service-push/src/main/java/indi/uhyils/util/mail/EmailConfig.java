package indi.uhyils.util.mail;

import java.io.Serializable;

/**
 * 邮件配置类，数据存覆盖式存入数据存
 *
 * @author uhyils@qq.com
 */
public class EmailConfig implements Serializable {

    private Long id;

    /**
     * 邮件服务器SMTP地址
     */
    private String host;

    /**
     * 邮件服务器SMTP端口
     */
    private String port;

    /**
     * 发件者用户名，默认为发件人邮箱前缀
     */
    private String user;

    private String pass;

    /**
     * 收件人
     */
    private String fromUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
}

