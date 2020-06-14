package indi.uhyils.pojo.request;

/**
 * 测试服务器链接
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 16时51分
 */
public class TestConnByDataRequest extends DefaultRequest {

    private String ip;

    private Integer port;

    private String username;

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
