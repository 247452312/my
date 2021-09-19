package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 中间件表(Software)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分23秒
 */
public class SoftwareDTO extends IdDTO {

    private static final long serialVersionUID = 979209625796205836L;


    private String other1;

    private String other2;

    private String password;

    private Integer port;

    private Long serverId;

    private String startSh;

    private Integer status;

    private String statusSh;

    private String stopSh;

    private Integer type;

    private String username;

    private String version;


    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }


    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
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


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
