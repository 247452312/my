package indi.uhyils.rpc.config;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月16日 10时32分
 */
public class RegistryConfig {

    /**
     * 注册中心地址
     */
    private String host;

    /**
     * 注册中心端口
     */
    private Integer port;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
