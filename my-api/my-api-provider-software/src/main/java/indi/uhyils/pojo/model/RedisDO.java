package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;

/**
 * redis
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 12时59分
 */
public class RedisDO extends BaseDoDO {


    /**
     * 密码
     */
    private String password;

    /**
     * 登录端口
     */
    private Integer port;

    /**
     * 服务器id
     */
    private Long serverId;

    /**
     * 开启服务命令
     */
    private String startSh;

    /**
     * 服务状态
     */
    private Integer status;

    /**
     * 查询状态命令
     */
    private String statusSh;

    /**
     * 停止服务sh
     */
    private String stopSh;

    /**
     * 版本
     */
    private String version;

    /**
     * other1 对应容器名称
     */
    private String dockerName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getStartSh() {
        return startSh;
    }

    public void setStartSh(String startSh) {
        this.startSh = startSh;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusSh() {
        return statusSh;
    }

    public void setStatusSh(String statusSh) {
        this.statusSh = statusSh;
    }

    public String getStopSh() {
        return stopSh;
    }

    public void setStopSh(String stopSh) {
        this.stopSh = stopSh;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDockerName() {
        return dockerName;
    }

    public void setDockerName(String dockerName) {
        this.dockerName = dockerName;
    }
}
