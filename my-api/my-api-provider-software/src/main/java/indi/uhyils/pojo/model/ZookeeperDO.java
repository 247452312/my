package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;

/**
 * zookeeper
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时04分
 */
public class ZookeeperDO extends BaseDoDO {

    /**
     * 登录用户名
     */
    private String username;

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
    private String serviceId;

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
     * 类型
     */
    private Integer type;

    /**
     * 版本
     */
    private String version;

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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
