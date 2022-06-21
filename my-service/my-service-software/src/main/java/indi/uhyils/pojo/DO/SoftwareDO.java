package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 中间件表(Software)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时14分03秒
 */
@TableName(value = "sys_software")
public class SoftwareDO extends BaseDO {

    private static final long serialVersionUID = -87740151705222023L;


    @TableField
    private String other1;

    @TableField
    private String other2;

    @TableField
    private String password;

    @TableField
    private Integer port;

    @TableField
    private Long serverId;

    @TableField
    private String startSh;

    @TableField
    private Integer status;

    @TableField
    private String statusSh;

    @TableField
    private String stopSh;

    @TableField
    private Integer type;

    @TableField
    private String username;

    @TableField
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
